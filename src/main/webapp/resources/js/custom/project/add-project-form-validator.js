/* 
 *add-project-form-validator
 */
$(document).ready(function () {


    // =================================================================
    var faIcon = {
        valid: 'fa fa-check-circle fa-lg text-success',
        invalid: 'fa fa-times-circle fa-lg',
        validating: 'fa fa-refresh'
    };

    // =================================================================
    $('#addPro').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {
            project_name: {
                validators: {
                        notEmpty: {
                        message: 'Please Enter Project Name.'
                    }
                }
            },
            client_name: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            start_date: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            }

        }
    });

});




