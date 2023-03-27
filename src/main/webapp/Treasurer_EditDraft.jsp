<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page errorPage="error.jsp"%>
<%@ page import="com.entity.*, java.util.*,com.database.*" %>
 <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <!-- CDN BOOTSTRAP -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <!-- css links  -->


    
    <link rel="stylesheet" href="css/admin.css">


    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/f8210108fd.js" crossorigin="anonymous"></script>

    <!-- CK Editor -->
  <script src="https://cdn.ckeditor.com/ckeditor5/35.1.0/super-build/ckeditor.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/35.1.0/classic/ckeditor.js"></script>
    

    <title>Barangay Website Builder</title>
</head>

<body>
    
    <% if(session.getAttribute("treasurer")==null){
	
	 response.sendRedirect(request.getContextPath() + "/Login");
}

else{
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	
	response.setDateHeader("Expires", -1);
	
}%>

    
    
    
<%User user=(User)session.getAttribute("treasurerData"); %>

    <div class="d-flex " id="wrapper">

        <!-- SIDE BAR OFF CANVASS -->

        <div class="bg-dark text-white" id="sidebar-wrapper">

            <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">

                <span><img src="image/logo7.png" alt="" class="img-fluid rounded-circle" height="50" width="50"></span>
                <p>BRGY WEB BUILDER</p>



            </div>

            <div class="list-group list-group-flush List-data">

               <a href="Treasurer" class="list-group-item list-group-item-action bg-transparent second-text">
                    <i class="fa-solid fa-gauge"></i>Dashboard
                </a>
                
                <a href="Treasure-managepost" class="list-group-item list-group-item-action bg-transparent fw-bold" data-bs-toggle="collapse" data-bs-target="#demo">
                    <i class="fa-solid fa-list-check"></i>Manage Post <i class="fa-solid fa-caret-down"></i>
                </a>
                <ul class="postNav navbar-nav  collapse" id="demo">
                    <li class="nav-item">
                        <a href="Treasure-managepost" class="nav-link">All Post</a>
                    </li>
                    <li class="nav-item">
                        <a href="Treasurer-addpost" class="nav-link">Add Post</a>
                    </li>
                    
                    

                </ul>




                <a href="Treasurer-draft" class="list-group-item list-group-item-action bg-transparent fw-bold active">
                    <i class="fa-solid fa-file"></i>Draft
                </a>

                <a href="<%=request.getContextPath() %>/Welcome" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-regular fa-eye"></i>View Website
                </a>
            



            </div>





        </div>

        <!-- SIDEBAR ENDS HERE -->


        <div id="page-content-wrapper">

            <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
                <div class="d-flex align-items-center">
                    <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle" onclick="click();"></i>
                    <h2 class="fs-4 m-0">Secretary Panel</h2>
                </div>


                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navigation" aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navigation">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle second-text fw-bold" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                               <img src="http://localhost:8080/CmsUploadImage/<%=user.getUser_image()%>" alt="" class="img-fluid rounded-circle sample">
                                <%=user.getUsername() %>
                            </a>
                          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="Treasurer-profile">Edit Profile</a></li>
                                <li><a class="dropdown-item" href="Treasurer-changepassword">Change Password</a></li>
                               <li><a class="dropdown-item" href="<%=request.getContextPath()%>/Controller_Treasurer_Logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">


<% 

int id=Integer.parseInt(request.getParameter("id"));


Dao_Secretary_Managepost execution=new Dao_Secretary_Managepost();

Secretary_Post draft=execution.getDraftData(id);

int catId=draft.getPostCat_id();
%>




                <h3 class="post-text">Edit Post</h3>
                <div class="form-container">
					
						<%if (request.getSession().getAttribute("msg_draft")!=null){ %>
						
						<p class="bg-danger p-2 text-white">${msg_draft}</p>

					<%
					request.getSession().removeAttribute("msg_draft");
					}
					%>

					<form action="<%=request.getContextPath() %>/Controller_TreasurerDraft" method="post" enctype="multipart/form-data" class="row">
                    
                    <input type="hidden" name="postId" placeholder="postID" value="<%=draft.getPostId()  %>">
                    
              <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
              
                     
                     
                     
                        <div class="col-lg-6 col-md mb-3">
                            <label for="" class="form-label">Post Title</label>
                            <input type="text" name="postTitle" placeholder="Enter Post Title" class="form-control" value="<%=draft.getPost_title() %>">
                        </div>



                        <div class="col-lg-6 col-md mb-3">
                            <label for="" class="form-label">Select Category</label>




                            <select name="category" id="" class="form-select" required>
                            <option value=<%=draft.getPostCat_id() %>><%=draft.getCategoryName() %></option>
                            <% Dao_Category categories=new Dao_Category(); %>
                            
                           <%List<Category> list=categories.showCategoryList(catId);   %>
                            
                            <%for (Category category:list){ %>
                            <option value=<%=category.getCategory_id() %>><%=category.getCategoryName() %></option>
                            
                            <%} %>
                          
                        </select>




                        </div>
                        <div>

                        </div>


                        <div class="col-lg-6 col-md mb-3">
                            <label for="" class="form-label">Cover Image</label>
                            <div class="cover-image"> <img src="http://localhost:8080/CmsUploadImage/<%=draft.getPost_image()%>" alt="" class="img-fluid"></div>
                            
                            <input type="file" name="file" id="" class="form-control">
                              <input type="hidden" name="oldfile" id="" class="form-control" value="<%=draft.getPost_image()  %>">
                        </div>

                        <div>

                        </div>

                       

                        <div class="col-lg-12 mb-3">
                            <label for="" class="form-label">Post Content</label>
                            <textarea name="content" id="editor" cols="30" rows="10" class="form-control">
								<%=draft.getPost_content() %>
                        </textarea>
                        
                        
                        </div>


                       

                        <div>
                            <button type="submit" class="btn btn-sm btn-dark" name="editdraft" value="updatedraft">Submit</button>
							 <button type="submit" class="btn btn-sm btn-danger" name="editdraft" value="draft">Draft</button>

                        </div>
                    </form>



                </div>




            </div>


        </div>

        <!-- CDN FOR JS     -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
 
    <!--  img in ck editor 'insertImage'  -->
 
<script>


CKEDITOR.ClassicEditor.create(document.getElementById("editor"), {
    // https://ckeditor.com/docs/ckeditor5/latest/features/toolbar/toolbar.html#extended-toolbar-configuration-format
    toolbar: {
        items: [
            'exportPDF', 'exportWord', '|',
            'findAndReplace', 'selectAll', '|',
            'heading', '|',
            'bold', 'italic', 'strikethrough', 'underline', 'code', 'subscript', 'superscript', 'removeFormat', '|',
            'bulletedList', 'numberedList', 'todoList', '|',
            'outdent', 'indent', '|',
            'undo', 'redo',
            '-',
            'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor', 'highlight', '|',
            'alignment', '|',
            'link', 'blockQuote',
            'specialCharacters', 'horizontalLine', 'pageBreak', , '|',

        ],
        shouldNotGroupWhenFull: true
    },
    // Changing the language of the interface requires loading the language file using the <script> tag.
    // language: 'es',
    list: {
        properties: {
            styles: true,
            startIndex: true,
            reversed: true
        }
    },
    
    // https://ckeditor.com/docs/ckeditor5/latest/features/headings.html#configuration
    heading: {
        options: [{
            model: 'paragraph',
            title: 'Paragraph',
            class: 'ck-heading_paragraph'
        }, {
            model: 'heading1',
            view: 'h1',
            title: 'Heading 1',
            class: 'ck-heading_heading1'
        }, {
            model: 'heading2',
            view: 'h2',
            title: 'Heading 2',
            class: 'ck-heading_heading2'
        }, {
            model: 'heading3',
            view: 'h3',
            title: 'Heading 3',
            class: 'ck-heading_heading3'
        }, {
            model: 'heading4',
            view: 'h4',
            title: 'Heading 4',
            class: 'ck-heading_heading4'
        }, {
            model: 'heading5',
            view: 'h5',
            title: 'Heading 5',
            class: 'ck-heading_heading5'
        }, {
            model: 'heading6',
            view: 'h6',
            title: 'Heading 6',
            class: 'ck-heading_heading6'
        }]
    },
    // https://ckeditor.com/docs/ckeditor5/latest/features/editor-placeholder.html#using-the-editor-configuration
    placeholder: 'Welcome to CKEditor 5!',
    // https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-family-feature
    fontFamily: {
        options: [
            'default',
            'Arial, Helvetica, sans-serif',
            'Courier New, Courier, monospace',
            'Georgia, serif',
            'Lucida Sans Unicode, Lucida Grande, sans-serif',
            'Tahoma, Geneva, sans-serif',
            'Times New Roman, Times, serif',
            'Trebuchet MS, Helvetica, sans-serif',
            'Verdana, Geneva, sans-serif',
            'Ubuntu, Arial, sans-serif',
            'Ubuntu Mono, Courier New, Courier, monospace'
        ],
        supportAllValues: true
    },
    // https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-size-feature
    fontSize: {
        options: [10, 12, 14, 'default', 18, 20, 22, 26,28,36,48,72],
        supportAllValues: true
    },
    // Be careful with the setting below. It instructs CKEditor to accept ALL HTML markup.
    // https://ckeditor.com/docs/ckeditor5/latest/features/general-html-support.html#enabling-all-html-features
    htmlSupport: {
        allow: [{
            name: /.*/,
            attributes: true,
            classes: true,
            styles: true
        }]
    },
    // Be careful with enabling previews
    // https://ckeditor.com/docs/ckeditor5/latest/features/html-embed.html#content-previews
    htmlEmbed: {
        showPreviews: true
    },
    // https://ckeditor.com/docs/ckeditor5/latest/features/link.html#custom-link-attributes-decorators
    link: {
        decorators: {
            addTargetToExternalLinks: true,
            defaultProtocol: 'https://',
            toggleDownloadable: {
                mode: 'manual',
                label: 'Downloadable',
                attributes: {
                    download: 'file'
                }
            }
        }
    },
    // https://ckeditor.com/docs/ckeditor5/latest/features/mentions.html#configuration
    mention: {
        feeds: [{
            marker: '@',
            feed: [
                '@apple', '@bears', '@brownie', '@cake', '@cake', '@candy', '@canes', '@chocolate', '@cookie', '@cotton', '@cream',
                '@cupcake', '@danish', '@donut', '@dragée', '@fruitcake', '@gingerbread', '@gummi', '@ice', '@jelly-o',
                '@liquorice', '@macaroon', '@marzipan', '@oat', '@pie', '@plum', '@pudding', '@sesame', '@snaps', '@soufflé',
                '@sugar', '@sweet', '@topping', '@wafer'
            ],
            minimumCharacters: 1
        }]
    },
    // The "super-build" contains more premium features that require additional configuration, disable them below.
    // Do not turn them on unless you read the documentation and know how to configure them and setup the editor.
    removePlugins: [
        // These two are commercial, but you can try them out without registering to a trial.
        // 'ExportPdf',
        // 'ExportWord',
        'CKBox',
        'CKFinder',
        'EasyImage',
        // This sample uses the Base64UploadAdapter to handle image uploads as it requires no configuration.
        // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/base64-upload-adapter.html
        // Storing images as Base64 is usually a very bad idea.
        // Replace it on production website with other solutions:
        // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/image-upload.html
        // 'Base64UploadAdapter',
        'RealTimeCollaborativeComments',
        'RealTimeCollaborativeTrackChanges',
        'RealTimeCollaborativeRevisionHistory',
        'PresenceList',
        'Comments',
        'TrackChanges',
        'TrackChangesData',
        'RevisionHistory',
        'Pagination',
        'WProofreader',
        // Careful, with the Mathtype plugin CKEditor will not load when loading this sample
        // from a local file system (file://) - load this site via HTTP server if you enable MathType
        'MathType'
    ]
});




</script>



     <script src="<%=request.getContextPath()%>/js/external.js"></script>


</body>


</html>