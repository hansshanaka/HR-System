/* 
 
 */
var table;

$(window).on('load', function () {

    // DATA TABLES
    // =================================================================
    // Require Data Tables
    // -----------------------------------------------------------------
    // http://www.datatables.net/
    // =================================================================


 $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/medicalReimb/pendingMedicalList").load();
 
    table = $('#demo-dt-basic').DataTable();

});
