/* 
 * 
 */


var table;

$(window).on('load', function () {


 $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/leave/leaveTypeList").load();
 
    table = $('#demo-dt-basic').DataTable();

    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        var data = table.row(this).data();
        var id = data[0];
        var name = data[1];
        var days = data[2];
        
        
        $("#ltype_id").val(id);
        $("#ltype_name").val(name);
        $("#days").val(days);      
        
         
        document.getElementById("save").style.display="none";
        document.getElementById("edit").style.display="inline";
        document.getElementById("close").style.display="inline";
               
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
