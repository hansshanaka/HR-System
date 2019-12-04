
// Tables-DataTables.js
// ====================================================================
// This file should not be included in your project.
// This is just a sample how to initialize plugins or components.
//
// - ThemeOn.net -


var table;

$(window).on('load', function () {


    // DATA TABLES
    // =================================================================
    // Require Data Tables
    // -----------------------------------------------------------------
    // http://www.datatables.net/
    // =================================================================

    $.fn.DataTable.ext.pager.numbers_length = 5;


    // Basic Data Tables with responsive plugin
    // -----------------------------------------------------------------
   /* $('#demo-dt-basic').dataTable({
        "responsive": true,
        "language": {
            "paginate": {
                "previous": '<i class="fa fa-angle-left"></i>',
                "next": '<i class="fa fa-angle-right"></i>'
            }
        }
    });*/


   // var table = $('#demo-dt-basic').DataTable();

    table = $('#demo-dt-basic').DataTable({
        "lengthMenu": [100, 250, 500],
        "processing": true,
        "serverSide": true,
        responsive: true,
        searching: false,
        "ajax": {
            "url": contextPath + "/salutation/a/m",
            "type": "GET"
        },
        "columns": [
            {"data": "salutationId"},
            {"data": "salutationName"},
            {"data": "salutationDescription"},
            {"data": "recordStatusDesc"},
            {"data": "inpUserid"},
            {"data": "inpDatetime"},
            {"data": "authUserid"},
            {"data": "authDatetime"}
        ]
    });

    /*$('#demo-dt-basic tbody').on('click', 'td', function ()
    {
        var tr = $(this).closest("tr");
        var rowindex = tr.index();
        var colIdx = table.cell(this).index().column;

        var data = table.row( tr ).data();
        alert( 'You clicked on '+data['salutationId']+'\'s row' );

        if (colIdx !== 0) {
            alert(rowindex + "- " + colIdx);
        }else{
            alert('Test');
        }

        var colIdx = table.cell(this).index().column;
        //alert( 'You clicked on '+table.cell(this).index().column+'\'s row' );

    });*/

    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        var id=data['salutationId'];
        alert( 'You clicked on '+table.row(this).index()+'\'s row'+ id );
        document.getElementById('salutationId').value=id;
        document.getElementById('frmForm').action = contextPath + "/salutation/n/m/edit";
        document.getElementById('frmForm').submit();
        //window.location.href ='/rsa/salutation/n/m/edit?salutationId='+id;
        ///
    } );


    // Row selection (single row)
    // -----------------------------------------------------------------
    /*var rowSelection = $('#demo-dt-selection').DataTable({
        "responsive": true,
        "language": {
            "paginate": {
                "previous": '<i class="fa fa-angle-left"></i>',
                "next": '<i class="fa fa-angle-right"></i>'
            }
        }
    });*/

    var rowSelection=table;
    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            rowSelection.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
 
                                                // 2016.01.28 custom
        var rowData = table.row(this).data();
        var cellData = rowData[0];
        alert(cellData)
       // window.location.href ='/RSAUITest/pages/AddSalutations.jsp?ID='+cellData;

    });






    // Row selection and deletion (multiple rows)
    // -----------------------------------------------------------------
    var rowDeletion = $('#demo-dt-delete').DataTable({
        "responsive": true,
        "language": {
            "paginate": {
                "previous": '<i class="fa fa-angle-left"></i>',
                "next": '<i class="fa fa-angle-right"></i>'
            }
        },
        "dom": '<"toolbar">frtip'
    });
    $('#demo-custom-toolbar').appendTo($("div.toolbar"));

    $('#demo-dt-delete tbody').on('click', 'tr', function () {
        $(this).toggleClass('selected');
    });

    $('#demo-dt-delete-btn').click(function () {
        rowDeletion.row('.selected').remove().draw(false);
    });






    // Add Row
    // -----------------------------------------------------------------
    var t = $('#demo-dt-addrow').DataTable({
        "responsive": true,
        "language": {
            "paginate": {
                "previous": '<i class="fa fa-angle-left"></i>',
                "next": '<i class="fa fa-angle-right"></i>'
            }
        },
        "dom": '<"newtoolbar">frtip'
    });
    $('#demo-custom-toolbar2').appendTo($("div.newtoolbar"));

    $('#demo-dt-addrow-btn').on('click', function () {
        t.row.add([
            'Adam Doe',
            'New Row',
            'New Row',
            nifty.randomInt(1, 100),
            '2015/10/15',
            '$' + nifty.randomInt(1, 100) + ',000'
        ]).draw();
    });


});

function reloadPage() {
   //alert($("#salutationName").val());
    table.ajax.url(contextPath + '/salutation/a/m?salutationName='+$("#salutationName").val()).load();
//                table.ajax.reload(function (json) {
//
//                    //$('#myInput').val( json.lastInput );
//                    alert("" + $("#usrstatus").val());
//                });
}
