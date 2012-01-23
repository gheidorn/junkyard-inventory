<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta name="keywords" content="" />
  		<meta name="description" content="" />
		<title><tiles:getAsString name="title"></tiles:getAsString></title>
		<!--[if IE]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js">
	    </script>
	    <![endif]-->
		<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/static/css/main.css" />
	</head>
<body>
	<tiles:insertAttribute name="header" />
    <div class="container">
    	<div class="content">
      		<tiles:insertAttribute name="content" />
      	</div>
      	<footer>
        	<tiles:insertAttribute name="footer" />
      	</footer>
    </div> <!-- /container -->
	<tiles:insertAttribute name="scripts" />
</body>
</html>