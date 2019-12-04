/* 
 * salaryInput-datatables
 */

var table;

$(window).on('load', function () {


    // DATA TABLES
    // =================================================================
    // Require Data Tables
    // -----------------------------------------------------------------
    // http://www.datatables.net/
    // =================================================================

        table = $('#demo-dt-basic').DataTable();
        $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/employee/getTmpSalary").load();
});

    



