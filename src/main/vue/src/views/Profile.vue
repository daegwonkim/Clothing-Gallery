<template>
  <div class="div-profile_sheet">
    <div>
      <div class="div-my_account">
        <div class="div-my_account_title">My Account</div>
        <div>View and edit your personal info below.</div>
      </div>
      <div class="div-set_account">
        <div class="div-set_account_title">Account</div>
        <div>Update your personal information.</div>
        <br />
        <div>Login Email:</div>
        <div>daegwonkim.dev@gmail.com</div>
        <div class="div-email_cant_be_changed">
          Your Login email can't be changed
        </div>
        <br />
        <v-form
          ref="accountForm"
          v-model="accountValid"
          lazy-validation
        >
          <div class="div-grid_container">
            <div v-for="(item, i) in inputItems" :key="i">
              <div>{{ item }}</div>
              <v-text-field
                class="v-text-field-input_account_info"
                clearable
                dense
                color="#666666"
                :rules="accountRules[i]"
              ></v-text-field>
            </div>
          </div>
        </v-form>
      </div>
      <div class="div-set_address">
        <div class="div-set_address_title">Address</div>
        <div>Update your address.</div>
        <br />

        <div>
          <div class="d-flex div-address_name_and_btn_wrapper">
            <div><font-awesome-icon icon="fa-solid fa-star" size="sm" style="color: #444444" /> My Home</div>
            <div>
              <button><font-awesome-icon icon="fa-solid fa-pen-to-square" style="color: #666666" /></button>&nbsp;
              <button><font-awesome-icon icon="fa-solid fa-xmark" style="color: #666666" /></button>
            </div>
          </div>
          <div>(123456) 충북 진천군 광혜원면 광혜원산단길 139</div>
          <div>석미모닝파크2차 201동 203호</div>
        </div>

        <!-- Set Address Dialog -->
        <v-form
          ref="addressForm"
          v-model="addressValid"
          lazy-validation
        >
          <div class="mt-3">
            <v-dialog v-model="dialog" persistent max-width="600px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="btn-account" dark v-bind="attrs" v-on="on" elevation="0">
                  Add New Address
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="text-h5">Add New Address</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          v-model="addressName"
                          label="Name*"
                          :rules="addressNameRules"
                          required
                          hint="Please set the name of your address."
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          v-model="zipcode"
                          label="Zip / Postal code*" 
                          :rules="addressCodeRules"
                          required
                          readonly
                          @focus="openPostCode"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="streetAddress"
                          label="Street address*"
                          :rules="streetAddressRules"
                          required
                          readonly
                          hint="Please enter it through the Zip / Postal code."
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="detailAddress"
                          label="Detail address"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" class="mt-n7 ml-n1">
                        <v-checkbox
                          v-model="checkbox"
                          label="Make this my default address"
                        ></v-checkbox>
                      </v-col>
                    </v-row>
                  </v-container>
                  <small>*indicates required field</small>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="darken-1" text @click="dialog = false">
                    Close
                  </v-btn>
                  <v-btn color="darken-1" text @click="addressValidate" :disabled="!addressValid">
                    Save
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </v-form>
        <!-- Set Address Dialog End -->

        <div class="mt-10" align="right">
          <v-btn class="btn-account mr-2" elevation="0" outlined>Discard</v-btn>
          <v-btn class="btn-account" dark elevation="0">Update Info</v-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      inputItems: ["Username", "Password", "Nickname", "Phone"],

      dialog: false,

      zipcode: "",
      streetAddress: "",
      detailAddress: "",

      addressValid: true,

      addressName: "",

      accountRules: [
        [v => !!v || "Username is required", v => (v && v.length <= 20) || "Username must be less than 20 characters"],
        [v => !!v || "Password is required"],
        [v => !!v || "Nickname is required"],
        [v => !!v || "Phone is required"],
      ],
      addressNameRules: [
        v => !!v || "Name is required",
        v => (v && v.length <= 20) || "Name must be less than 20 characters"
      ],
      addressCodeRules: [
        v => !!v || "Zip / Postal code is required"
      ],
      streetAddressRules: [
        v => !!v || "Street address is required"
      ]
    };
  },

  methods: {
    // Kakao Address API
    openPostCode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          this.zipcode = data.zonecode;
          this.streetAddress = data.roadAddress;
        }
      }).open();
    },

    addressValidate() {
      if(this.$refs.addressForm.validate()) {
        this.dialog = false;
      }
    }
  },
};
</script>

<style>
.div-profile_sheet {
  margin: 50px auto 50px auto;
  padding: 30px;
  border: 1px solid #cccccc;
  width: 600px;
  height: auto;
}

.div-my_account {
  border-bottom: 1px solid #cccccc;
  height: 92px;
}

.div-my_account_title {
  font-size: 30px;
}

.div-set_account,
.div-set_address {
  margin-top: 20px;
}

.div-set_account_title,
.div-set_address_title {
  font-weight: bold;
  font-size: 20px;
}

.div-email_cant_be_changed {
  color: #aaaaaa;
}

.div-grid_container {
  display: grid;
  grid-template-columns: auto auto;
  gap: 30px;
  margin-bottom: 30px;
}

.div-address_name_and_btn_wrapper {
  justify-content: space-between;
}

.btn-account {
  border-radius: 0;
  text-transform: none;
}

.btn-account::before {
  background-color: black !important;
}

.v-text-field-input_account_info {
  width: 250px;
}
</style>