/*Javascript����Ƭ�Σ�����ʹ��jQuery*/

    function check_values() {
        if ($("#username").val().length !== 0 && $("#password").val().length !== 0) {

            $("#loginbtn").animate({ left: '0' , duration: 'slow'});
            $("#lockbtn").animate({ left: '260px' , duration: 'slow'});
        }
    }

			
	$("#loginbtn").click(function(){
		$('#loading').removeClass('hidden');
		//��¼��غ�̨��������: Ajax����
	});