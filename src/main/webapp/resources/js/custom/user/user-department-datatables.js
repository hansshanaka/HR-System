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

//    $.fn.DataTable.ext.pager.numbers_length = 5;
//
    if (recordtype == "i") {
        table = $('#demo-dt-basic').DataTable();
    } else {
        table = $('#demo-dt-basic').DataTable();
    }


    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        
        var data = table.row(this).data();
        var departmetID = data[0];
        document.getElementById('userDep').action = contextPath + "/userDepartment/editDepart/"+departmetID;
        document.getElementById('userDep').submit();

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


});

function searchUserDep() {
    var departId = $("#userDepList").val();
    $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/userDepartment/getDepartment?depCode="+departId).load();

}
