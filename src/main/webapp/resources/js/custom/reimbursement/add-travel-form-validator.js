/* 
 * add-travel-form-validator
 */

$(document).ready(function () {


    // =================================================================
    var faIcon = {
        valid: 'fa fa-check-circle fa-lg text-success',
        invalid: 'fa fa-times-circle fa-lg',
        validating: 'fa fa-refresh'
    };

    // =================================================================
    $('#addTravel').bootstrapValidator({
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
            amount: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            image: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            start: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            end: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            },
            distance: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
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


