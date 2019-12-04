/**
 * Created by User on 2/7/2016.
 */
function customNiftyNoty(type, msg) {//type=success,
    var icon = "fa ";
    if (type === 'success') {
        icon += 'fa-check';
    } else if (type === 'danger') {
        icon += 'fa-close';
    } else if (type === 'warning') {
        icon += 'fa-warning';
    } else if (type === 'info') {
        icon += 'fa-info';
    }
    $.niftyNoty({
        type: type,
        icon: icon,
        message: '<span style="font-size: 12px;">' + msg + '</span>',
        container: 'floating',
        timer: 30000
    });
}

/**
 * This function is used to validate alphanumeric values
 * Written by: Sahan Ranasinghe on 16/02/2016
 */
var alphanumericValidationCallback = {
    message: 'Only letters and numbers are allowed.',
    callback: function (value, validator, $field) {
        if (value.length === 0)
            return true;
        var letterNumber = /^[0-9a-zA-Z]+$/;
        var r = value.match(letterNumber);
        if (r === null)
            return false;
        if (r)
            return true;
        else
            return false;
    }
};

/**
 * This function is used to validate numeric values
 * Written by: Sahan Ranasinghe on 16/02/2016
 */
var numericValidationCallback = {
    message: 'Only numbers are allowed.',
    callback: function (value, validator, $field) {
        if (value.length === 0)
            return true;
        var number = /^[0-9]+$/;
        var r = value.match(number);
        if (r === null)
            return false;
        if (r)
            return true;
        else
            return false;
    }
};

/**
 * This function is used to validate currency values
 * Written by: Sahan Ranasinghe on 16/02/2016
 */
var currencyValidationCallback = {
    message: 'Invalid Currency Format (###.##)',
    callback: function (value, validator, $field) {
        if (value.length === 0)
            return true;
        var number = /^[\d]+[.][\d]{2}$/;
        var r = value.match(number);
        if (r === null)
            return false;
        if (r)
            return true;
        else
            return false;
    }
};


/**
 * This function is used to validate values with letters and spaces
 * Written by: Hasitha on 16/02/2016
 */
var lettersAndSpaceValidationCallback = {
    message: 'Only letters  and spaces are allowed.',
    callback: function (value, validator, $field) {
        if (value.length === 0)
            return true;
        var letter = /^[a-z\s]+$/i;
        var r = value.match(letter);
        if (r === null)
            return false;
        if (r)
            return true;
        else
            return false;
    }
};


/**
 * This function is used to validate SVAT Number
 * Written by: Hasitha on 16/02/2016
 */
var SVATNumberValidationCallback = {
    message: 'Invalid SVAT Number Format.(SVATXXXXX)',
    callback: function (value, validator, $field) {
        if (value.length === 0)
            return true;
        var number = /^[SVAT]+[0-9]{5}$/;
        var r = value.match(number);
        if (r === null)
            return false;
        if (r)
            return true;
        else
            return false;
    }
};


/**
 * This function is used to validate VAT Reg Number
 * Written by: Hasitha on 16/02/2016
 */
var VATNRegumberValidationCallback = {
    message: 'Invalid VAT Reg Number Format.(XXXXXXXXX-XXXX)',
    callback: function (value, validator, $field) {
        if (value.length === 0)
            return true;
        var number = /^[0-9]{9}[-]+[0-9]{4}$/;
        var r = value.match(number);
        if (r === null)
            return false;
        if (r)
            return true;
        else
            return false;
    }
};

/**
 * This function is used to ignore Enter key presses on form inputs.
 * Written by: Sahan Ranasinghe on 17/02/2016
 */
var ignoreEnterKeyOnForms = function () {
    $(window).keydown(function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });
};

/**
 * Disable all form input, select and textarea elements,
 * except input[type=button|submit|reset] elements
 * @param form the id of the form
 * Written by: Sahan Ranasinghe on 17/02/2016
 */
var disableFormInputs = function (form) {
    $(form + " input").prop("readonly", true);
    $(form + ' input[type=radio], '+ form + ' input[type=checkbox]').prop("disabled", true);
    $(form + " textarea").prop("readonly", true);
    $(form + " input[type=button]").prop("readonly", false);
    $(form + " input[type=submit]").prop("readonly", false);
    $(form + " input[type=reset]").prop("readonly", false);
    try {
        $(form + " select").prop("disabled", true).trigger("chosen:updated");
    } catch (e) {
    }

};

/**
 * Enable all form input, select and textarea elements,
 * @param form the id of the form
 * Written by: Sahan Ranasinghe on 17/02/2016
 */
var enableFormInputs = function (form) {
    $(form + " input").prop("readonly", false);
    $(form + " select").prop("readonly", false);
    $(form + " textarea").prop("readonly", false);
};

var roundToTwoNumbers = {

    message: 'Invalid format (ex: ###.## or ###)',
    callback: function (value, validator, $field) {

        if (value.length === 0)
            return true;
        var number = /^[\d]+([.][\d]{2}){0,1}$/;
        var r = value.match(number);
        if (r === null)
            return false;
        if (r)
            return true;
        else
            return false;
    }
};

var formatDate = function (inputFormat) {
    function pad(s) {
        return (s < 10) ? '0' + s : s;
    }

    var d = new Date(inputFormat);
    return [pad(d.getMonth() + 1), pad(d.getDate()), d.getFullYear()].join('/');
}