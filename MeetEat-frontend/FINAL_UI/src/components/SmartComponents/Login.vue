<template>
   <div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <form onsubmit="event.preventDefault()" class="box">
                      <h1>Log in</h1> 
                      <input type="text" name="" placeholder="Email address"> 
                      <input type="password" name="" placeholder="Password">  
                      <input type="submit" name="" value="Login" href="#">
                      <a class="text-muted" href="#">Don't have an account yet ?</a>
                    <div class="col-md-12">
                    </div>
                </form>
            </div>
        </div>
    </div>
 </div>
</template>

<script>
export default {
  name: "Login" //même nom que le nom de fichier
}
</script>

<style scoped>

.card {
    margin-bottom: 20px;
    border: none
}

.box {
    width: 800px;
    padding: 50px;
    position: absolute;
    top: 50%;
    left: 50%;
    background: #191919;
    ;
    text-align: center;
    transition: 0.25s;
    margin-top: 100px;
}

.box input[type="text"],
.box input[type="password"] {
    border: 0;
    background: white;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #3498db;
    padding: 10px 10px;
    width: 300px;
    outline: none;
    color: black;
    border-radius: 5px;
    transition: 0.25s
}

.box h1 {
    color: white;
    text-align: left;
    text-transform: none;
    font-weight: 500
}

.box input[type="text"]:focus,
.box input[type="password"]:focus {
    width: 300px;
    border-color: #2ecc71
}

.box input[type="submit"] {
    border: 0;
    background: green;
    text-transform: uppercase;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #2ecc71;
    padding: 10px 70px;
    outline: none;
    color: white;
    border-radius: 5px;
    transition: 0.25s;
    cursor: pointer
}

.box input[type="submit"]:hover {
    background: #2ecc71
}

.forgot {
    text-decoration: underline
}

</style>