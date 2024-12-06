<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Preferential</title>
</head>
<body>
<h1>Add Preferential</h1>
<form action="${pageContext.request.contextPath}/addPreferential" method="post" enctype="multipart/form-data">
    <label>Code:</label>
    <input type="text" name="preferentialCode" required><br>
    <label>Name:</label>
    <input type="text" name="preferentialName" required><br>
    <label>Start Day:</label>
    <input type="date" name="startDay" required><br>
    <label>End Day:</label>
    <input type="date" name="endDay" required><br>
    <label>Quantity:</label>
    <input type="number" name="quantity" required><br>
    <label>Rate:</label>
    <input type="number" step="0.01" name="rate" required><br>
    <label>Description:</label>
    <textarea name="preferentialDescription" required></textarea><br>
    <label>Image:</label>
    <input type="file" name="preferentialImg"><br>
    <label>Employee ID:</label>
    <input type="text" name="employeeID" required><br>
    <button type="submit">Submit</button>
</form>
</body>
</html>
