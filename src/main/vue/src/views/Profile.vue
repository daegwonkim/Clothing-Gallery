<template>
  <div>
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
          <div>{{beforeAccount.username}}</div>
          <div class="div-email_cant_be_changed">
            Your Login email can't be changed
          </div>
          <br />

          <!-- Input Account Info -->
          <v-form
            ref="accountForm"
            v-model="accountValid"
            lazy-validation
          >
            <div class="div-grid_container">
              <div>
                <div>Username</div>
                <v-text-field
                  v-model="account.username"
                  class="v-text-field-input_account_info"
                  dense
                  color="#666666"
                  disabled
                ></v-text-field>
              </div>
              <div>
                <div>Password</div>
                <v-text-field
                  v-model="account.password"
                  class="v-text-field-input_account_info"
                  :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="showPassword ? 'text' : 'password'"
                  clearable
                  dense
                  counter
                  color="#666666"
                  :rules="accountRules.password"
                  @click:append="showPassword = !showPassword"
                ></v-text-field>
              </div>
              <div>
                <div>Nickname</div>
                <v-text-field
                  v-model="account.nickname"
                  class="v-text-field-input_account_info"
                  clearable
                  dense
                  counter
                  color="#666666"
                  :rules="accountRules.nickname"
                ></v-text-field>
              </div>
              <div>
                <div>Phone</div>
                <v-text-field
                  v-model="account.phone"
                  class="v-text-field-input_account_info"
                  dense
                  color="#666666"
                  @keyup="getPhoneMask(account.phone)"
                  :rules="accountRules.phone"
                ></v-text-field>
              </div>
            </div>
          </v-form>
          <!-- Input Account Info End -->
        </div>

        <div class="my-5" align="right">
          <v-btn class="btn-account mr-2" :class="{'disable-events': !isChanged}" elevation="0" outlined @click="discardDialog = true">Discard</v-btn>
          <v-btn class="btn-account" :class="{'disable-events': !isChanged}" dark elevation="0" @click="updateInfo">Update Info</v-btn>
        </div>

        <div class="div-set_address">
          <div class="div-set_address_title">Address</div>
          <div>Add and manage the addresses you use often.</div>
          <br />

          <div class="div-saved_any_addresses_yet" v-if="addresses.length == 0">You haven't saved any addresses yet.</div>

          <div class="div-addresses" v-if="addresses.length > 0">
            <div class="div-addresses_item" v-for="(address, i) in addresses" :key="i">
              <div class="d-flex div-address_name_and_btn_wrapper">
                <div>
                  <font-awesome-icon v-if="address.defaultAddress" icon="fa-solid fa-star" size="sm" style="color: #444444" />
                  {{address.name}}
                </div>
                <div>
                  <button @click="editAddress(address, i)"><font-awesome-icon icon="fa-solid fa-pen-to-square" style="color: #666666" /></button>&nbsp;
                  <button @click="removeAddress(address.id, i)"><font-awesome-icon icon="fa-solid fa-xmark" style="color: #666666" /></button>
                </div>
              </div>
              <div>({{address.zipcode}}) {{address.street}}</div>
              <div>{{address.detail}}</div>
            </div>
          </div>

          <!-- Set Address Dialog -->
          <v-form
            ref="addressForm"
            v-model="addressValid"
          >
            <div class="mt-3">
              <v-dialog v-model="addressDialog" max-width="600px">
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
                            :rules="addressRules.addressNameRules"
                            required
                            hint="Please set the name of your address."
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                          <v-text-field
                            v-model="zipcode"
                            label="Zip / Postal code*" 
                            :rules="addressRules.addressCodeRules"
                            required
                            readonly
                            @focus="openPostCode"
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12">
                          <v-text-field
                            v-model="streetAddress"
                            label="Street address*"
                            :rules="addressRules.streetAddressRules"
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
                            v-model="isDefault"
                            label="Make this my default address"
                          ></v-checkbox>
                        </v-col>
                      </v-row>
                    </v-container>
                    <small>*indicates required field</small>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="darken-1" text @click="resetDialog">
                      Close
                    </v-btn>
                    <v-btn color="darken-1" text @click="addNewAddress" :disabled="!addressValid">
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </div>
          </v-form>
          <!-- Set Address Dialog End -->
        </div>
      </div>
    </div>

    <!-- Discard Dialog -->
    <v-dialog
      v-model="discardDialog"
      max-width="450"
    >
      <v-card>
        <v-card-title class="text-h5">
          Discard Changes?
        </v-card-title>
        <v-card-text>Are you sure you want to discard the changes you've made?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="dark darken-1"
            text
            @click="discardDialog = false"
          >
            Cancel
          </v-btn>
          <v-btn
            color="dark darken-1"
            text
            @click="discard"
          >
            Discard
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- Discard Dialog End -->

    <!-- Update Info Alert -->
    <div align="center">
      <v-alert
        class="alert"
        type="success"
        :value="successAlert"
        dense
        transition="fade-transition"
        width="fit-content"
      >Profile updated</v-alert>
    </div>

    <div align="center">
      <v-alert
        class="alert"
        type="error"
        :value="errorAlert"
        dense
        transition="fade-transition"
        width="fit-content"
      >Account must be valid</v-alert>
    </div>
    <!-- Update Info Alert End -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      isChanged: false,
      isPasswordChanged: false,
      isNicknameChanged: false,
      isPhoneChanged: false,

      account: null,
      beforeAccount: null,
      addresses: [],

      showPassword: false,
      phoneNumber: "",

      successAlert: false,
      errorAlert: false,
      addressDialog: false,
      discardDialog: false,
      mode: "add",

      curAddressId: null,
      curAddressIndex: null,

      zipcode: "",
      streetAddress: "",
      detailAddress: "",
      isDefault: false,

      addressValid: true,

      addressName: "",

      accountRules: {
        password: [
          v => !!v || "Password is required", 
          v => (v && 8 <= v.length && v.length <= 16 && /^(?=.*[A-Za-z])(?=.*\d)(?=.*[.`!@#$%^&*?])[A-Za-z\d@.`!@#$%^&*?]{8,16}$/.test(v)) || "Password must be between 8 and 16 characters including english, numbers, and special characters."
        ],
        nickname: [
          v => !!v || "Nickname is required",
          v => (v && 2 <= v.length && v.length <= 10) || "Nickname must be between 2 and 10 characters"
        ],
        phone: [
          v => !!v || "Phone is required",
          v => /\d{3}-\d{3,4}-\d{4}/.test(v) || "Phone must be valid"
        ]
      },
      addressRules: {
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
      }
    };
  },

  watch: {
    account: {
      handler() {
        this.isPasswordChanged = this.account.password != this.beforeAccount.password ? true : false;  
        this.isNicknameChanged = this.account.nickname != this.beforeAccount.nickname ? true : false; 
        this.isPhoneChanged = this.account.phone != this.beforeAccount.phone ? true : false;

        this.isChanged = this.isPasswordChanged || this.isNicknameChanged || this.isPhoneChanged;
      },
      deep: true,
    },
  },

  mounted() {
    this.getAccountInfo();
  },

  methods: {
    getAccountInfo() {
      this.$axios.get("/api/customer/get/1").then((res) => {
        this.account = res.data;
        this.beforeAccount = JSON.parse(JSON.stringify(res.data));  // Deep copy
      });

      this.$axios.get("/api/address/get/1").then((res) => {
        res.data.forEach(element => {
          this.addresses.push(element);
        });
      });
    },

    getPhoneMask(value) {
      this.account.phone = this.getMask(value)
    },
    
    getMask(phoneNumber) {
      if(!phoneNumber) return phoneNumber
      phoneNumber = phoneNumber.replace(/[^0-9]/g, '')
  
      let res = ''
      if(phoneNumber.length < 3) {
          res = phoneNumber
      }
      else {
        if(phoneNumber.substr(0, 2) =='02') {
          if(phoneNumber.length <= 5) { //02-123-5678
            res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3)
          } else if(phoneNumber.length > 5 && phoneNumber.length <= 9) { //02-123-5678
            res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3) + '-' + phoneNumber.substr(5)
          } else if(phoneNumber.length > 9) { //02-1234-5678
            res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 4) + '-' + phoneNumber.substr(6)
          }
        } 
        else {
          if(phoneNumber.length < 8) {
            res = phoneNumber
          } else if(phoneNumber.length == 8) {
            res = phoneNumber.substr(0, 4) + '-' + phoneNumber.substr(4)
          } else if(phoneNumber.length == 9) {
            res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
          } else if(phoneNumber.length == 10) {
            res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
          } else if(phoneNumber.length > 10) { //010-1234-5678
            res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7)
          }
        }
      }
                  
      return res
    },

    // Kakao Address API
    openPostCode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          this.zipcode = data.zonecode;
          this.streetAddress = data.roadAddress;
        }
      }).open();
    },

    addNewAddress() {
      if(!this.$refs.addressForm.validate()) return;

      let newAddress = {
        name: this.addressName,
        zipcode: this.zipcode,
        street: this.streetAddress,
        detail: this.detailAddress,
        defaultAddress: this.isDefault,
      };

      if(this.isDefault) {
        this.addresses.forEach(element => {
          if(element.defaultAddress) {
            element.defaultAddress = false;
          }
        });
      }
      
      if(this.mode == "add") {
        this.$axios.post("/api/address/add", {customer: this.account, address: newAddress}).then((res) => {
          this.addresses = [];

          this.$axios.get("/api/address/get/1").then((res) => {
            res.data.forEach(element => {
              this.addresses.push(element);
            });
          });
        });
      } else {
        newAddress.id = this.curAddressId;
        this.$axios.put("/api/address/edit", newAddress).then((res) => {
          this.$set(this.addresses, this.curAddressIndex, newAddress);
          this.mode = "add";
        });
      }

      this.resetDialog();
    },

    editAddress(address, i) {
      this.addressName = address.name;
      this.zipcode = address.zipcode;
      this.streetAddress = address.street;
      this.detailAddress = address.detail;
      this.isDefault = address.defaultAddress;

      this.addressDialog = true;
      this.mode = "edit";
      this.curAddressId = address.id;
      this.curAddressIndex = i;
    },

    removeAddress(id, i) {
      this.$axios.delete(`/api/address/delete/${id}`).then((res) => {
        this.$delete(this.addresses, i);
      });
    },

    resetDialog() {
      this.mode = "add";

      this.addressName = '';
      this.zipcode = '';
      this.streetAddress = '';
      this.detailAddress = '';
      this.isDefault = false;

      this.addressDialog = false;

      this.$refs.addressForm.resetValidation();
    },

    discard() {
      this.$set(this.account, "password", this.beforeAccount.password);
      this.$set(this.account, "nickname", this.beforeAccount.nickname);
      this.$set(this.account, "phone", this.beforeAccount.phone);

      this.discardDialog = false;
    },

    updateInfo() {
      if(this.$refs.accountForm.validate()) {
        this.$axios.put("/api/customer/update", this.account).then((res) => {
          this.account = res.data;
          this.beforeAccount = JSON.parse(JSON.stringify(res.data));  // Deep copy

          this.successAlert = true;

          setTimeout(() => {
            this.successAlert = false;
          }, 2000);
        });
      } else {
        this.errorAlert = true;

        setTimeout(() => {
          this.errorAlert = false;
        }, 2000);
      }
    },
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

.div-saved_any_addresses_yet {
  color: #aaaaaa;
}

.div-addresses .div-addresses_item:not(:last-of-type) {
  padding-bottom: 10px;
  margin-bottom: 10px;
  border-bottom: 1px dotted #cccccc;
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

.disable-events {
  pointer-events: none;
  opacity:0.6;
}

.v-text-field-input_account_info {
  width: 250px;
}

.v-text-field-input_phone .v-input__slot {
  width: 60px;
}

.v-text-field-input_phone input {
  text-align: center;
}

.alert {
  position: fixed;
  top: 16%;
  left: 50%;
  transform: translate(-50%, -50%);
  margin: 0 auto;
}
</style>