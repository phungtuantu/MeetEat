<template>
  <div class="container">
      <div class="row">
          <div class="col-md-6">
              <div class="card">
                  <form onsubmit="event.preventDefault()" class="box">
                      <h1>Login</h1>
                      <input type="text" name="" placeholder="Email address"> 
                      <input type="password" name="" placeholder="Password"> 
                      <input type="submit" name="" value="Login" href="#">
                      <div class="column">
                        <a class="text-muted" href="#">Don't have an account yet ?</a>
                        <button class="btn" type="button">Create an account</button>
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
    width: 500px;
    padding: 20px;
    position: absolute;
    top: 50%;
    left: 50%;
    background: #191919;
    transition: 0.25s;
    margin-top: 100px
}

.box input[type="text"],
.box input[type="password"] {
    border: 0;
    background: white;
    display: block;
    margin: 20px auto;
    text-align: left;
    border: 2px solid #3498db;
    padding: 10px 10px;
    width: 400px;
    outline: none;
    color: black;
    border-radius: 5px;
    transition: 0.25s
}

.box h1 {
    color: white;
    text-transform: none;
    text-align: left;
    font-weight: 500
}

.box input[type="text"]:focus,
.box input[type="password"]:focus {
    width: 100%;
    border-color: #2ecc71
}

.box input[type="submit"] {
    border: 0;
    width: 400px;
    background: green;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #2ecc71;
    padding: 14px 40px;
    outline: none;
    color: white;
    text-transform: uppercase;
    border-radius: 5px;
    transition: 0.25s;
    cursor: pointer
}

.box input[type="submit"]:hover {
    background: #2ecc71;
    width: 100%;
}

.column{
  text-align: left;
}

.text-muted {
    margin-left : 30px;
    text-decoration: underline;
}

.btn{
  margin-right : 30px;
  float: right;
  background-color: cyan;
  text-transform: none;
}

</style>