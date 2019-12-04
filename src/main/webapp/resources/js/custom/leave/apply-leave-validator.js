/* 
 * 
 */


$(document).ready(function () {


    // =================================================================
    var faIcon = {
        valid: 'fa fa-check-circle fa-lg text-success',
        invalid: 'fa fa-times-circle fa-lg',
        validating: 'fa fa-refresh'
    };

    // =================================================================
    $('#leaveForm').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {
            leaveTypeList: {
                validators: {
                        notEmpty: {
                        message: 'Please Leave Type.'
                    }
                }
            },
//            start_date: {
//                validators: {
//                        notEmpty: {
//                        message: 'Please select date.'
//                    }
//                }
//            },
//            end_date: {
//                validators: {
//                        notEmpty: {
//                        message: 'Please select date.'
//                    }
//                }
//            },
            no_of_days: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            }

        }
    });

});
