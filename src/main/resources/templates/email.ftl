<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Esta es una prueba de envio de email usando freemaker</title>
    <style>
        #saludo{
            font-size: large;
            color: darkgrey;
        }
    </style>
</head>
<body>
<h1>${user.subject}</h1>
<div id="saludo"> Hola, estimado ${user.name}</div>
<div><b>${user.body}</b></div>
<br/>
<div> Que tenga un lindo dia!</div>
<div> Saludos</div>
</body>
</html>