<template>
  <div>
    <div class="d-flex div-newletter_contact_wrapper">
      <div>
        <v-responsive class="mx-auto" width="370">
          <div align="center">NEWSLETTER</div><br>
          <div class="d-flex mt-2">
            <v-text-field
              class="v-text-field-email_for_newletter"
              label="Email address"
              type="email"
              hint="Enter your email and subscribe to the website"
              clearable
              outlined
              dense
            ></v-text-field>
            <v-btn
              class="btn-join_newsletter"
              outlined
              height="40"
            >
              Join
            </v-btn>
          </div>
        </v-responsive>
      </div>

      <div align="center">
        <div class="div-font_size_small">
          <a href="mailto:evolveclothinggallery@gmail.com">evolveclothinggallery@gmail.com</a><br>
          908.231.8933<br><br>
          80 W Main Street<br>
          Somerville, NJ 08876
        </div>
        <div>
          <v-btn icon>
            <font-awesome-icon icon="fa-brands fa-facebook" />
          </v-btn>
          <v-btn icon>
            <font-awesome-icon icon="fa-brands fa-twitter" />
          </v-btn>
          <v-btn icon>
            <font-awesome-icon icon="fa-brands fa-instagram" />
          </v-btn>
        </div>
      </div>
    </div>

    <div class="div-footer" align="center">
      <v-btn
          v-for="link in links"
          :key="link"
          text
          class="mx-2 btn-footer_btn"
          rounded="xl"
          v-ripple="false"
      >
        {{ link }}
      </v-btn>

      <div class="div-footer_card">
        We Accept
        <font-awesome-icon icon="fa-brands fa-cc-visa" style="color: #ffffff;" size="2xl" />
        <font-awesome-icon icon="fa-brands fa-cc-mastercard" style="color: #ffffff;" size="2xl" />
        <font-awesome-icon icon="fa-brands fa-cc-amex" style="color: #ffffff;" size="2xl" />
        <font-awesome-icon icon="fa-brands fa-cc-discover" style="color: #ffffff;" size="2xl" />
        <font-awesome-icon icon="fa-brands fa-cc-paypal" style="color: #ffffff;" size="2xl" />
      </div>
    </div>

    <v-footer class="bg-grey-lighten-1">
      <v-row justify="center" no-gutters>
        <v-col class="text-center v-col-footer">
          <div>© 2023 by Baeg-won Clothing Gallery.</div>
          <div>Proudly created with Vuetify</div>
        </v-col>
      </v-row>
    </v-footer>
  </div>
</template>

<script>
export default {
  data: () => ({
    links: ["CONTACT US", "ABOUT", "RETURNS & SHIPPING", "TERMS & CONDITION", "FAQ's"],
  }),
};
</script>

<style>
.div-newletter_contact_wrapper {
  justify-content: space-around;
  margin: 60px 0 60px 0;
}

.v-text-field-email_for_newletter {
  border-radius: 0px;
}

.btn-join_newsletter {
  border-radius: 0px;
  border-left: none;
}

.div-font_size_small {
  font-size: small;
}

.div-footer {
  background-color: black;
  height: 240px;
}

.btn-footer_btn {
  color: white !important;
  top: 80px;
}

.div-footer_card {
  color: white;
  font-size: 13px;
  margin-top: 150px;
}

.div-footer_card > svg {
  margin-left: 10px;
}

.v-col-footer {
  font-size: 13px;
  color: #999999;
}
</style>