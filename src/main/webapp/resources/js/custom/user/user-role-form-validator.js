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
                        max: 30,
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
                        max: 70,
                        message: 'Characters amount is out of range'
                    }
                }
            },
            userTypeList: {
                //container: 'tooltip',
                validators: {
                    notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }

                }
            }
        }
//        ,
//        rules: {
//            userTypeList: {// <- NAME attribute of the input
//                required: {
//                    depends: function () {
//                        return ($("#userTypeList").val() == "0");
//                    }
//                }
//            }
//        }

    });

});



//$(document).ready(function() {
//    $('#userRole').formValidation({
//        framework: 'bootstrap',
//        icon: {
//            valid: 'glyphicon glyphicon-ok',
//            invalid: 'glyphicon glyphicon-remove',
//            validating: 'glyphicon glyphicon-refresh'
//        },
//        fields: {
//            'menuItem': {
//                validators: {
//                    choice: {
//                        min: 2,
//                        max: 4,
//                        message: 'Please choose 2 - 4 programming languages you are good at'
//                    }
//                }
//            }
//        }
//    });
//});


$(document).ready(function () {

});