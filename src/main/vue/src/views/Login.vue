<template>
  <div class="div-login_form_wrapper" align="center">
    <div class="div-login_form_title">Sign In</div>
    <div>New to this site?</div>
    <v-form
      class="mt-5"
      ref="loginForm"
      v-model="valid"
      lazy-validation
    >
      <v-text-field
        class="v-text-field-input_account"
        v-model="username"
        :rules="[v => !!v || 'E-mail is required']" 
        label="E-mail"
        required
      ></v-text-field>

      <v-text-field
        class="v-text-field-input_account"
        v-model="password"
        :rules="[v => !!v || 'Password is required']"
        label="Password"
        required
      ></v-text-field>

      <div class="mt-3" align="left">Forgot password?</div>

      <div>
        <v-btn
          class="btn-login mt-7"
          dark
          elevation="0"
        >
          Sign In
        </v-btn>
      </div>
    </v-form>

    <div class="div-text_decoration">
      <div>or log in with</div>
    </div>

    <div>
      <font-awesome-icon class="mr-3" icon="fa-brands fa-google" size="xl" />
      <font-awesome-icon class="ml-3" icon="fa-brands fa-facebook" size="xl" />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {

    }
  },
}
</script>

<style>
.div-login_form_wrapper {
  margin: 50px auto 50px auto;
  padding: 30px;
  border: 1px solid #cccccc;
  width: 400px;
  height: auto;
}

.div-login_form_title {
  font-size: 30px;
}

.btn-login {
  border-radius: 0px;
}

.div-text_decoration {
  height: 13px;
  border-bottom: 1px solid #cccccc;
  margin: 20px 0 30px 0;
}

.div-text_decoration > div {
  background-color: white; 
  width: 120px;
}
</style>