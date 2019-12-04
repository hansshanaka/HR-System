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


 $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/user/userTypeList").load();
 
    table = $('#demo-dt-basic').DataTable();

    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        var data = table.row(this).data();
        var id = data[0];
        var name = data[1];
        
        $("#user_type_id").val(id);
        $("#typeId").val(id);
        $("#type_name").val(name);    
        
        $('#user_type_id').attr('readonly', true);
        
        
        
         
         document.getElementById("save").style.display="none";
         document.getElementById("edit").style.display="inline";
//         document.getElementById("delete").style.display="inline";
         document.getElementById("close").style.display="inline";
         
//         document.getElementsByClassName()("panel-body").hidden = true;
               
//        document.getElementById('userType').action = contextPath + "/user/" + recordtype + "/saveUserType";
//        document.getElementById('userType').submit();
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



