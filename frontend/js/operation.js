     $(function() {  
        var stu_number = $("#stu-number"),
        name = $( "#name" ),  
        department = $( "#department" ),  
        total = $( "#total" ), 
        usual_grade = $( "#usual"),
        design_grade = $("#design"),
        exam_grade = $( "#exam"), 
        rowindex = $( "#rowindex" ),  
        allFields = $( [] ).add( stu_number ).add( name ).add( department ).add( total ).add( rowindex );  

        $( "#dialog-form" ).dialog({  
            autoOpen: false,  
            height: 300,  
            width: 480,  
            modal: true,  
            buttons: {  
                "新建": function() {  
                    if (rowindex.val()==""){//新增 
                        var student = new Object();
                        student.id = stu_number.val();
                        student.name = name.val();
                        student.department = department.val();
                        student.total = total.val();
                        student.usual_grade = "0";
                        student.design_grade = "0";
                        student.exam_grade = "0";

                          $.ajax({
                            url: 'http://localhost:9000/students/add',
                            type: 'POST',
                            cache: false,
                            dataType:'json',
                            data: student
                        }).done(function(res) {
                            console.log(res);
                        }).fail(function(res) {
                            console.log(res);
                        });

                        $( "#users tbody" ).append( "<tr>" +  
                            "<td>" + stu_number.val() + "</td>" +   
                            "<td>" + name.val() + "</td>" +   
                            "<td>" + department.val() + "</td>" +  
                            "<td>" + total.val() + "</td>" + 
                            '<td><button class="EditButton">修改</button><button class="DeleteButton">删除</button><button class="GradeButton">成绩</button></td>'+  
                            "</tr>" );   
                        bindEvent();  
                    }  
                    else{//修改  
                        var idx = rowindex.val();  
                        var tr = $("#users>tbody>tr").eq(idx);  
                        //$("#debug").text(tr.html());  
                        tr.children().eq(0).text(name.val());  
                        tr.children().eq(1).text(email.val());  
                        tr.children().eq(2).text(password.val());  
                    }  
                    $( this ).dialog( "close" );  
                },  
                "取消": function() {  
                    $( this ).dialog( "close" );  
                }  
            },  
            close: function() {  
                //allFields.val( "" ).removeClass( "ui-state-error" );  
                ;  
            }  
        });

        $( "#dialog-modify" ).dialog({  
            autoOpen: false,  
            height: 300,  
            width: 480,  
            modal: true,  
            buttons: {  
                "确认": function() {  
                    if (rowindex.val()==""){//新增 
                        var student = new Object();
                        student.id = stu_number.val();
                        student.name = name.val();
                        student.department = department.val();
                        student.total = total.val();
                        student.usual_grade = usual_grade.val();
                        student.design_grade = design_grade.val();
                        student.exam_grade = exam_grade.val();

                          $.ajax({
                            url: 'http://localhost:9000/students/add',
                            type: 'POST',
                            cache: false,
                            dataType:'json',
                            data: student,
                            // headers : {
                            //      'Accept' : 'application/json',
                            //      'Content-Type' : 'application/json'
                            //    }
                        }).done(function(res) {
                            console.log(res);
                        }).fail(function(res) {
                            console.log(res);
                        });

                        $( "#users tbody" ).append( "<tr>" +  
                            "<td>" + stu_number.val() + "</td>" +   
                            "<td>" + name.val() + "</td>" +   
                            "<td>" + department.val() + "</td>" +  
                            "<td>" + total.val() + "</td>" + 
                            '<td><button class="EditButton">修改</button><button class="DeleteButton">删除</button><button class="GradeButton">成绩</button></td>'+  
                            "</tr>" );   
                        bindEvent();  
                    }  
                    else{//修改  
                        var idx = rowindex.val();  
                        var tr = $("#users>tbody>tr").eq(idx);  
                        //$("#debug").text(tr.html());  
                        tr.children().eq(0).text(name.val());  
                        tr.children().eq(1).text(email.val());  
                        tr.children().eq(2).text(password.val());  
                    }  
                    $( this ).dialog( "close" );  
                },  
                "取消": function() {  
                    $( this ).dialog( "close" );  
                }  
            },  
            close: function() {  
                //allFields.val( "" ).removeClass( "ui-state-error" );  
                ;  
            }  
        });

        $( "#dialog-import").dialog({
            autoOpen: false,
            height: 200,
            width: 320,
            modal: true,
            buttons: {
                "导入": function(){
                    $.ajax({
                        url: 'http://localhost:9000/batchimport',
                        type: 'POST',
                        cache: false,
                        data: new FormData($('#uploadForm')[0]),
                        processData: false,
                        contentType: false
                    }).done(function(res) {
                        console.log(res);
                    }).fail(function(res) {

                    });
                    $(this).dialog("close");
                },
                "取消": function(){
                    $(this).dialog("close");
                }
            },
            close: function(){
                ;
            }
        });

        $( "#dialog-grade").dialog({
            autoOpen: false,
            height: 300,
            width: 480,
            modal: true,
            buttons: {
                "确认": function(){
                    /**/
                    $(this).dialog("close");
                },
                "取消": function(){
                    $(this).dialog("close");
                }
            },
            close: function(){
                ;
            }
        });

        function bindEvent(){  
            //绑定Edit按钮的单击事件  
            $(".EditButton").click(function(){  
                var b = $(this);  
                var tr = b.parents("tr");  
                var tds = tr.children();  
                    //设置初始值  
                    stu_number.val(tds.eq(0).text());
                    name.val(tds.eq(1).text());  
                    department.val(tds.eq(2).text());  
                    total.val(tds.eq(3).text());  

                    var trs = b.parents("tbody").children();  
                    //设置行号，以行号为标识，进行修改。  
                    rowindex.val(trs.index(tr));  

                    //打开对话框  
                    $( "#dialog-form" ).dialog( "open" );  
                });  

            //绑定Delete按钮的单击事件  
            $(".DeleteButton").click(function(){  
                var tr = $(this).parents("tr");  
                tr.remove();  
            }); 

            //绑定Grade按钮的单击事件
            $(".GradeButton").click(function(){
                $("#dialog-grade").dialog("open");
            });
        };  

        bindEvent();  

        $( "#create-user" )  
        .button()  
        .click(function() {  
                //清空表单域  
                allFields.each(function(idx){  
                    this.value="";  
                });  
                $( "#dialog-form" ).dialog( "open" );  
            });  

        $("#import")
        .button()
        .click(function(){
                //导入本地文件  
                $( "#dialog-import" ).dialog( "open" );  
            })
    });



