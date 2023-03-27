<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <%@ page errorPage="error.jsp"%>
<%@ page import="com.entity.*, java.util.*,com.database.*" %>

<%User user=(User)session.getAttribute("data");%>



   <div class="table-responsive">

                                <table class="table table-striped">

                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Category Name</th>

                                            <th scope="col">Action</th>


                                        </tr>
                                    </thead>

                                    <tbody>
                                    
                                    
                                    <%
                                    int count=0;
                                    
                                    Dao_Category categories=new Dao_Category(); %>
                            
                           <%List<Category> list=categories.listOfCategories(); %>
                            
                            <%for (Category category:list){
                            	count++;
                            	%>
                                        <tr>
                                            <th scope="row"><%=count %></th>
                                            <td style="display: none;"><%=category.getCategory_id() %></td>
                                            <td><%=category.getCategoryName() %></td>
                                            <td>

                                                <div class="formBtn">

                                                    <a href="Admin-categories?id=<%=category.getCategory_id() %>" class="btn btn-sm btn-success">Edit</a>
                                                    <a type="button" class="btn btn-sm btn-secondary deleteBtn">Archive</a>
                                                </div>
                                            </td>


                                        </tr>
      <%} %>


                                    </tbody>











                                </table>

<div class="modal fade" id="deletemodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"> Confirm Archive Category </h5>
                    <button type="button"  class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form action="<%=request.getContextPath() %>/Controller_CategoryDelete" method="post">

                    <div class="modal-body">

                        <input type="hidden" name="delete_id" id="delete_id">
						
						        
               <!-- Audit trail of Data  Start here -->
               
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
              
						
						
						
                        <p>Are you sure you want to archive this data?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" name="deletedata" class="btn btn-danger">Archive</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
  
   
                      

                            </div>
                            
      <script>
      
      $(document).ready(function () {

          $('.deleteBtn').on('click', function () {

              $('#deletemodal').modal('show');

              $tr = $(this).closest('tr');

              var data = $tr.children("td").map(function () {
                  return $(this).text();
              }).get();

              console.log(data);

              $('#delete_id').val(data[0]);

          });
      });
      
      </script>                      
                            
                            
    
   