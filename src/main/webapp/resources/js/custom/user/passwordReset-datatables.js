/* 
 * 
 */

var table;

$(window).on('load', function () {

    // DATA TABLES
    // =================================================================
    // Require Data Tables
    // -----------------------------------------------------------------
    // http://www.datatables.net/
    // =================================================================


 $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/user/getAllEmploys").load();
 
    table = $('#demo-dt-basic').DataTable();

    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        var data = table.row(this).data();
        var code = data[0];        
        $("#user_code").val(code);
                
    });

    var rowSelection = table;
    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            rowSelection.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    // =================================================================


});



