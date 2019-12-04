/* 
 * user role validater
 */

$(document).ready(function () {


    // =================================================================
    var faIcon = {
        valid: 'fa fa-check-circle fa-lg text-success',
        invalid: 'fa fa-times-circle fa-lg',
        validating: 'fa fa-refresh'
    };

    // =================================================================
    $('#userRole').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {
            user_role_id: {
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
            role_name: {
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
            }

        }
    });

});
