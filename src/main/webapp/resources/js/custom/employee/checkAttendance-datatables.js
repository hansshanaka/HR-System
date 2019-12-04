
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

        table = $('#demo-dt-basic').DataTable();
 




});

function checkAttendance() {
    var yearVal;
    if($("#year").val() == ""){
        yearVal = 0;
    }else{
        yearVal = $("#year").val();
    }
    
    var monthVal;
    if($("#month").val() == ""){
        monthVal = 0;
    }else{
        monthVal = $("#month").val();
    }
    
    var dayVal;
    if($("#day").val() == ""){
        dayVal = 0;
    }else{
        dayVal = $("#day").val();
    }
  
     
    $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/employee/checkEmpAttendances?year="+yearVal+"&month="+monthVal+"&day="+dayVal).load();

}
