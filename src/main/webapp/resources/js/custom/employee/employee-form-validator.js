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
    $('#addEmp')
            
            .bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {
            title: {
                validators: {
                        notEmpty: {
                        message: 'Please select title.'
                    }
                }
            },
            name: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    },
                    regexp: {
                        regexp: /^[A-Z\s]/i,
                        message: 'Invalid attempt'
                    }
                }
            },
            use_name: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            address: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            gender: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            dob: {
                validators: {
                        notEmpty: {
                        message: 'Please select date.'
                    }
                }
            },
            mobile_no: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            nic: {
                validators: {
                        notEmpty: {
                        message: 'Please Enter NIC.'
                    }
                }
            },
            marital_status: {
                validators: {
                        notEmpty: {
                        message: 'Please Select.'
                    }
                }
            },
            join_date: {
                validators: {
                        notEmpty: {
                        message: 'Please select date.'
                    }
                }
            },
            department_code: {
                validators: {
                        notEmpty: {
                        message: 'Please select department code.'
                    }
                }
            },
            category_id: {
                validators: {
                        notEmpty: {
                        message: 'Please select category.'
                    }
                }
            },
            user_type_id: {
                validators: {
                        notEmpty: {
                        message: 'Please select user type.'
                    }
                }
            },
            status_id: {
                validators: {
                        notEmpty: {
                        message: 'Please select user status.'
                    }
                }
            },salary: {
                validators: {
                        notEmpty: {
                        message: 'Please Enter Salary'
                    }
                }
            },email: {
                validators: {
                        notEmpty: {
                        message: 'Please Enter Email'
                    }
                }
            }

        }
    });

});


$(document).ready(function() {
    $('#addEmp').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                // All the email address field have emailAddress class
                selector: '.emailAddress',
                validators: {
                    callback: {
                        message: 'You must enter at least one email address',
                        callback: function(value, validator, $field) {
                            var isEmpty = true,
                                    // Get the list of fields
                                    $fields = validator.getFieldElements('email');
                            for (var i = 0; i < $fields.length; i++) {
                                if ($fields.eq(i).val() !== '') {
                                    isEmpty = false;
                                    break;
                                }
                            }

                            if (!isEmpty) {
                                // Update the status of callback validator for all fields
                                validator.updateStatus('email', validator.STATUS_VALID, 'callback');
                                return true;
                            }

                            return false;
                        }
                    },
                    emailAddress: {
                        message: 'The value is not a valid email address'
                    }
                }
            }
        }
    });
});

var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];    
function ValidateSingleInput(addFood) {
    if (addFood.type == "file") {
        var sFileName = addFood.value;
         if (sFileName.length > 0) {
            var blnValid = false;
            for (var j = 0; j < _validFileExtensions.length; j++) {
                var sCurExtension = _validFileExtensions[j];
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                    break;
                }
            }
             
            if (!blnValid) {
                alert("Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
                addFood.value = "";
                return false;
            }
        }
    }
    return true;
}

