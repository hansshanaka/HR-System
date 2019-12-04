/* 
 * 
 */

$(document).ready(function () {
    $('#activityRep').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            start_date: {
                validators: {
                    notEmpty: {
                        message: 'The field can not be empty'
                    }
                }
            }
        }
    });
});
