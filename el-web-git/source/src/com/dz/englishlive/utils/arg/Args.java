package com.dz.englishlive.utils.arg;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2014-6-4
 */
public class Args
{
    private String schema;
    private String[] args;
    private boolean valid;
    private Set<Character> unexpectArguments = new TreeSet<Character>();
    private Map<Character, Boolean> booleanArgs = new HashMap<Character, Boolean>();
    private Map<Character, String> stringArgs = new HashMap<Character, String>();
    private Map<Character, Integer> intArgs = new HashMap<Character, Integer>();
    private Set<Character> argsFound = new HashSet<Character>();
    private int currentArgument;
    private char errorArgumentId = '\0';
    private String errorParameter = "TILT";
    private ErrorCode errorCode = ErrorCode.OK;

    private enum ErrorCode
    {
        OK, MISSING_STRING, MISSING_INTEGER, INVALID_INTEGER, UNEXPECTED_ARGMENT
    }

    public Args(String schema, String[] args) throws ParseException
    {
        this.schema = schema;
        this.args = args;
        valid = parse();
    }

    private boolean parse() throws ParseException
    {
        if (schema.length() == 0 && args.length == 0)
        {
            return true;
        }
        parseSchema();
        try
        {
            parseArgments();
        }
        catch (ArgsException e)
        {
            // TODO: handle exception
        }
        return valid;
    }

    private boolean parseSchema() throws ParseException
    {
        for (String element : schema.split(","))
        {
            if (element.length() > 0)
            {
                String trimmedElement = element.trim();
                parseSchemaElement(trimmedElement);
            }
        }
        return true;
    }

    private void parseSchemaElement(String element) throws ParseException
    {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);
        if (isBooleanSchemaElement(elementTail))
        {
            parseBooleanSchemaElement(elementId);
        }
        else if (isStringSchemaElement(elementTail))
        {
            parseStringSchemaElement(elementId);
        }
        else if (isIntegerSchemaElement(elementTail))
        {
            parseIntegerSchemaElement(elementId);
        }
        else
        {
            throw new ParseException(String.format(
                    "Argment: %c has invalid format: %s.", elementId,
                    elementTail), 0);
        }
    }

    private boolean isIntegerSchemaElement(String elementTail)
    {
        return elementTail.equals("#");
    }

    private boolean isStringSchemaElement(String elementTail)
    {
        return elementTail.equals("*");
    }

    private boolean isBooleanSchemaElement(String elementTail)
    {
        return elementTail.length() == 0;
    }

    private void parseIntegerSchemaElement(char elementId)
    {
        intArgs.put(elementId, 0);
    }

    private void parseStringSchemaElement(char elementId)
    {
        stringArgs.put(elementId, "");

    }

    private void parseBooleanSchemaElement(char elementId)
    {
        booleanArgs.put(elementId, false);

    }

    private boolean parseArgments() throws ArgsException
    {
        for (currentArgument = 0; currentArgument < args.length; currentArgument++)
        {
            String arg = args[currentArgument];
            parseArgument(arg);
        }
        return true;
    }

    private void parseArgument(String arg) throws ArgsException
    {
        if (arg.startsWith("-"))
        {
            parseElements(arg);
        }
    }

    private void parseElements(String arg) throws ArgsException
    {
        for (int i = 1; i < arg.length(); i++)
        {
            parseElement(arg.charAt(i));
        }

    }

    private void parseElement(char argChar) throws ArgsException
    {
        if (setArgment(argChar))
            argsFound.add(argChar);
        else
        {
            unexpectArguments.add(argChar);
            errorCode = ErrorCode.UNEXPECTED_ARGMENT;
            valid = false;
        }

    }

    private boolean setArgment(char argChar) throws ArgsException
    {
        if (isBooleanArg(argChar))
            setBooleanArg(argChar, true);
        else if (isStringArg(argChar))
            setStringArg(argChar);
        else if (isIntArg(argChar))
            setIntArg(argChar);
        else
            return false;
        return true;
    }

    private void setBooleanArg(char argChar, boolean value)
    {
        booleanArgs.put(argChar, value);

    }

    private boolean isBooleanArg(char argChar)
    {
        return booleanArgs.containsKey(argChar);
    }

    private boolean isStringArg(char argChar)
    {
        return stringArgs.containsKey(argChar);
    }

    private void setStringArg(char argChar) throws ArgsException
    {
        currentArgument++;
        try
        {
            stringArgs.put(argChar, args[currentArgument]);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            valid = false;
            errorArgumentId = argChar;
            errorCode = ErrorCode.MISSING_STRING;
            throw new ArgsException();
        }
    }

    private void setIntArg(char argChar) throws ArgsException
    {
        currentArgument++;
        String parameter = null;
        try
        {
            parameter = args[currentArgument];
            intArgs.put(argChar, new Integer(parameter));
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            valid = false;
            errorArgumentId = argChar;
            errorCode = ErrorCode.MISSING_INTEGER;

            throw new ArgsException();
        }
        catch (NumberFormatException e)
        {
            valid = false;
            errorArgumentId = argChar;
            errorParameter = parameter;
            errorCode = ErrorCode.INVALID_INTEGER;
            throw new ArgsException();
        }
    }

    private boolean isIntArg(char argChar)
    {
        return intArgs.containsKey(argChar);
    }

    public int cardinality()
    {
        return argsFound.size();
    }

    public String usage()
    {
        if (schema.length() > 0)
        {
            return "-[" + schema + "]";
        }
        return "";
    }

    public String errorMessage() throws Exception
    {
        switch (errorCode)
        {
            case OK:
                throw new Exception("TILT : should not get here.");
            case UNEXPECTED_ARGMENT:
                return unexpectArgumentsMessage();
            case MISSING_STRING:
                return String.format("Could not find string paramter for -%c",
                        errorArgumentId);
            case MISSING_INTEGER:
                return String.format(
                        "Argument -%c expects an integer but was '%s'.",
                        errorArgumentId, errorParameter);
        }
        return "";
    }

    private String unexpectArgumentsMessage()
    {
        StringBuffer message = new StringBuffer("Argument(s) -");
        for (char c : unexpectArguments)
        {
            message.append(c);
        }
        return message.toString();
    }

    private boolean falseIfNull(Boolean b)
    {
        return b != null && b;
    }

    private int zeroIfNull(Integer i)
    {
        return i == null ? 0 : i;
    }

    private String blankIfNull(String s)
    {
        return s == null ? "" : s;
    }

    public int getInt(char arg)
    {
        return zeroIfNull(intArgs.get(arg));
    }

    public String getString(char arg)
    {
        return blankIfNull(stringArgs.get(arg));
    }

    public boolean has(char arg)
    {
        return argsFound.contains(args);
    }

    public boolean isValid()
    {
        return valid;
    }

    public boolean getBoolean(char arg)
    {
        return falseIfNull(booleanArgs.get(arg));
    }

    private void validateSchemaElementId(char elementId) throws ParseException
    {
        if (!Character.isLetter(elementId))
        {
            throw new ParseException("Bad character:" + elementId
                    + "in Args format:" + schema, 0);
        }
    }

    private class ArgsException extends Exception
    {

    }

}
