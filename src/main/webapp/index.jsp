<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
 <head>
   <meta charset="UTF-8">
   <meta name="description" content="Titles Library">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">

   <title>Titles Library</title>

   <!-- General Stylesheet links -->

   <!--  SITE STYLES  -->
   <link rel="stylesheet" href="CSS/basestyle.css">
   <link rel="stylesheet" href="CSS/homepage.css">
   <!-- Page specific styles -->
   <style>
     .btn-group form   { width: 95%; }
     .btn-group button { width: 95%; }
     .btn-group form button { width: 95%; }
   </style>

   <!-- JQuery and other local scripts -->

   <!-- INJECTION CODE FOR OTHER HTML -->
   <script>
      $(document).ready(function()
      {
        $('#myfooter').load('myfooter.html');
      });
   </script>  
 </head>

 <body>
  <div id="base_container">
     <header style="text-align: center;">Titles Library</header>
     <div style="width: 90%; margin-left: 25%;">
       <div class="btn-group" style="width: 10%; float: left;"> 
         <form method="GET" action="indexServlet">
           <button id="btnTM" name="titlesList" value="titlesList" type="submit">Search</button>
           <button id="btnPT" type="submit" value="PrintTitles">Print Titles</button>
           <button id="btnPA" type="submit" value="PrintAuthors">Print Authors</button>
           <button id="btnPM" type="submit" value="PrintMedia">Print Media</button>
           <button id="btnHelp" type="submit" value="Help">Help</button>
         </form>
       </div>
       <div id="bookshelf" style="width:40%; float: left;">
         <img src="Images/Bookshelf.png" style="width: 100%;" alt="MyBooks"/>
       </div>
        </div>
       <div class="btn-group" style="width: 10%; float: left;">
         <form method="GET" action="indexServlet">
          <button id="btnUsrLogin"    name="usr-login"    value="btn-usrlogin" type="submit">Login</button>
          <button id="btnUsrRegister" name="usr-register" value="btn-usrreg"   type="submit">Register</button>
          <button id="btnExit" name="btnExit" type="submit" value="btnExit">Exit</button>
         </form>
         </div>
     <div id="myfooter"></div>
   </div>
 </body>
</html>