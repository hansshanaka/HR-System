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
    $('#userType').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {
            user_type_id: {
                //container: 'tooltip',
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: 'Characters amount is out of range'
                    }
                }
            },
            type_name: {
                //container: 'tooltip',
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: 'Characters amount is out of range'
                    }
                }
            }

        }
    });

});
