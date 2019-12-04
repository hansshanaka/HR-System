

/* 
 * 
 */

$(document).ready(function () {

//    ignoreEnterKeyOnForms();
//    if (recordtype === 'a') {
//        disableFormInputs('#userType');
//    }

    // FORM VALIDATION FEEDBACK ICONS
    // =================================================================
    var faIcon = {
        valid: 'fa fa-check-circle fa-lg text-success',
        invalid: 'fa fa-times-circle fa-lg',
        validating: 'fa fa-refresh'
    };

    // =================================================================
    $('#userPReset').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {            
            user_code: {
                validators: {
                    notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            }
        }
    });

});
