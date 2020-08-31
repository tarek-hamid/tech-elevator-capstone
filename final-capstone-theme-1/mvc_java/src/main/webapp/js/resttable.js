jQuery(function($) {

    $('#employees').DataTable( {
        "processing": true,
        "serverSide": false,
        "order": [[ 4, "desc" ]],
        "ajax": {
            "url": "/user/rest/employees",
            "type": "GET"
        },
        "columns": [
            { "data": "firstName" },
            { "data": "lastName" },
            { "data": "position" },
            { "data": "office" },
            { "data": "startDate" },
            { "data": "salary" }
        ]
    } );
});