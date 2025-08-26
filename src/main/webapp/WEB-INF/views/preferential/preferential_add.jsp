<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Add Preferential</title>--%>
<%--    <style>--%>
<%--        body {--%>
<%--            font-family: Arial, sans-serif;--%>
<%--            background-color: #f4f4f4;--%>
<%--            margin: 0;--%>
<%--            padding: 0;--%>
<%--        }--%>

<%--        h1 {--%>
<%--            text-align: center;--%>
<%--            color: #333;--%>
<%--            padding: 20px;--%>
<%--        }--%>

<%--        form {--%>
<%--            background-color: white;--%>
<%--            padding: 20px;--%>
<%--            max-width: 600px;--%>
<%--            margin: 20px auto;--%>
<%--            border-radius: 8px;--%>
<%--            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);--%>
<%--        }--%>

<%--        label {--%>
<%--            font-weight: bold;--%>
<%--            margin-bottom: 8px;--%>
<%--            display: block;--%>
<%--            color: #333;--%>
<%--        }--%>

<%--        input, textarea, button {--%>
<%--            width: 100%;--%>
<%--            padding: 10px;--%>
<%--            margin-bottom: 15px;--%>
<%--            border: 1px solid #ccc;--%>
<%--            border-radius: 4px;--%>
<%--            font-size: 16px;--%>
<%--        }--%>

<%--        input[type="file"] {--%>
<%--            padding: 5px;--%>
<%--        }--%>

<%--        button {--%>
<%--            background-color: #4CAF50;--%>
<%--            color: white;--%>
<%--            border: none;--%>
<%--            cursor: pointer;--%>
<%--            font-size: 16px;--%>
<%--            text-align: center;--%>
<%--        }--%>

<%--        button:hover {--%>
<%--            background-color: #45a049;--%>
<%--        }--%>

<%--        textarea {--%>
<%--            resize: vertical;--%>
<%--            height: 100px;--%>
<%--        }--%>

<%--        .form-group {--%>
<%--            margin-bottom: 15px;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<jsp:include page="/WEB-INF/views/include/header.jsp" />--%>
<%--<h1>Add Preferential</h1>--%>
<%--<form action="${pageContext.request.contextPath}/addPreferential" method="post" enctype="multipart/form-data">--%>
<%--    <div class="form-group">--%>
<%--        <label>Code:</label>--%>
<%--        <input type="text" name="preferentialCode" required><br>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <label>Name:</label>--%>
<%--        <input type="text" name="preferentialName" required><br>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <label>Start Day:</label>--%>
<%--        <input type="date" name="startDay" required><br>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <label>End Day:</label>--%>
<%--        <input type="date" name="endDay" required><br>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <label>Quantity:</label>--%>
<%--        <input type="number" name="quantity" required><br>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <label>Rate:</label>--%>
<%--        <input type="number" step="0.01" name="rate" required><br>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <label>Description:</label>--%>
<%--        <textarea name="preferentialDescription" required></textarea><br>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <label>Image:</label>--%>
<%--        <input type="file" name="preferentialImg"><br>--%>
<%--    </div>--%>
<%--    <button type="submit">Submit</button>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%--</html>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Preferential</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
            padding: 20px;
        }

        form {
            background-color: white;
            padding: 20px;
            max-width: 600px;
            margin: 20px auto;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        label {
            font-weight: bold;
            margin-bottom: 8px;
            display: block;
            color: #333;
        }

        input, textarea, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        input[type="file"] {
            padding: 5px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
        }

        button:hover {
            background-color: #45a049;
        }

        textarea {
            resize: vertical;
            height: 100px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .error {
            color: red;
            font-size: 14px;
            margin-top: -10px;
            margin-bottom: 10px;
        }
    </style>
    <script>
        function validateForm(event) {
            const form = document.forms[0];

            // Get form values
            const code = form["preferentialCode"].value.trim();
            const name = form["preferentialName"].value.trim();
            const startDay = form["startDay"].value;
            const endDay = form["endDay"].value;
            const quantity = form["quantity"].value;
            const rate = form["rate"].value;
            const description = form["preferentialDescription"].value.trim();

            let isValid = true;
            const errors = {};

            // Validate Code
            if (code === "" || code.length < 3) {
                errors["preferentialCode"] = "Code must be at least 3 characters long.";
                isValid = false;
            }

            // Validate Name
            if (name === "" || name.length < 3) {
                errors["preferentialName"] = "Name must be at least 3 characters long.";
                isValid = false;
            }

            // Validate Dates
            if (startDay === "") {
                errors["startDay"] = "Start day is required.";
                isValid = false;
            }
            if (endDay === "") {
                errors["endDay"] = "End day is required.";
                isValid = false;
            } else if (startDay > endDay) {
                errors["endDay"] = "End day must be after Start day.";
                isValid = false;
            }

            // Validate Quantity
            if (quantity === "" || isNaN(quantity) || quantity <= 0) {
                errors["quantity"] = "Quantity must be a positive number.";
                isValid = false;
            }

            // Validate Rate
            if (rate === "" || isNaN(rate) || rate <= 0 || rate > 100) {
                errors["rate"] = "Rate must be a number between 0 and 100.";
                isValid = false;
            }

            // Validate Description
            if (description === "" || description.length < 10) {
                errors["preferentialDescription"] = "Description must be at least 10 characters long.";
                isValid = false;
            }

            // Clear previous errors
            document.querySelectorAll(".error").forEach((error) => error.remove());

            // Display errors
            Object.keys(errors).forEach((field) => {
                const inputField = form[field];
                const errorMessage = document.createElement("div");
                errorMessage.className = "error";
                errorMessage.textContent = errors[field];
                inputField.parentElement.appendChild(errorMessage);
            });

            if (!isValid) {
                event.preventDefault(); // Prevent form submission if invalid
            }
        }

        window.onload = function () {
            const form = document.querySelector("form");
            form.addEventListener("submit", validateForm);
        };
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<h1>Add Preferential</h1>
<form action="${pageContext.request.contextPath}/addPreferential" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label>Code:</label>
        <input type="text" name="preferentialCode" required>
    </div>
    <div class="form-group">
        <label>Name:</label>
        <input type="text" name="preferentialName" required>
    </div>
    <div class="form-group">
        <label>Start Day:</label>
        <input type="date" name="startDay" required>
    </div>
    <div class="form-group">
        <label>End Day:</label>
        <input type="date" name="endDay" required>
    </div>
    <div class="form-group">
        <label>Quantity:</label>
        <input type="number" name="quantity" required>
    </div>
    <div class="form-group">
        <label>Rate:</label>
        <input type="number" step="0.01" name="rate" required>
    </div>
    <div class="form-group">
        <label>Description:</label>
        <textarea name="preferentialDescription" required></textarea>
    </div>
    <div class="form-group">
        <label>Image:</label>
        <input type="file" name="preferentialImg">
    </div>
    <button type="submit">Submit</button>
</form>
</body>
</html>
