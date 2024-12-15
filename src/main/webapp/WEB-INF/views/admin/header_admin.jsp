<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding-top: 60px; /* Adjust for fixed header */
            background-color: #f8f9fa; /* Light background for better contrast */
        }

        .container-header {
            display: flex;
            justify-content: space-between;
            background-color: #343a40; /* Darker header for admin */
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
            padding: 10px 20px;
            color: white; /* Text color for header */
        }

        .container-header .logo h1 {
            color: #ff4880; /* Maintain brand color */
            margin: 0; /* Remove default margin */
        }

        .container-header .title {
            font-size: 20px; /* Increase title font size */
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .user-info img {
            width: 50px; /* Ensure the avatar fits better */
            height: 50px; /* Consistent size for avatars */
            border-radius: 50%;
            margin-top: 5px;
        }

        .nav-link {
            background-color: #ff4880; /* Maintain brand background for links */
            color: white; /* Text color for links */
            padding: 8px 12px;
            border-radius: 4px; /* Rounded corners for links */
            text-decoration: none; /* Remove underline */
            transition: background-color 0.3s; /* Smooth transition */
        }

        .nav-link:hover {
            background-color: #e03c3c; /* Darker shade on hover */
        }

        .admin-panel {
            padding: 20px; /* Padding for admin content */
        }

        /* Additional styles for admin navigation, tables, etc. */
        .admin-navigation {
            background-color: #f8f9fa; /* Light background for navigation */
            display: flex;
            flex-direction: column;
        }

        .admin-navigation a {
            padding: 10px;
            color: #343a40; /* Dark text for links */
            text-decoration: none;
            border-bottom: 1px solid #ddd; /* Divider */
        }

        .admin-navigation a:hover {
            background-color: #e9ecef; /* Light hover effect */
        }
    </style>
</head>
<body>
<div class="container-header">
    <div class="info-shop d-flex" style="align-items: center;">
        <div class="logo">
            <a href="dashboard" class="navbar-brand" style="text-decoration: none;">
                <h1 class="display-6">Baby<span style="color: #42a5f5;">Care</span></h1>
            </a>
        </div>
        <div class="title">
            <span>Admin Dashboard</span>
        </div>
    </div>
</div>

</body>
</html>
