<template>
  <v-container class="pa-16">
    <div align="center">
      <div class="div-cart_section" align="left">
        <div class="div-cart_title">My Cart</div>
        <div class="div-cart_list">
          <!-- Cart Item -->
          <div class="d-flex div-cart_list_item" v-for="(item, i) in productList" :key="i">
            <div class="div-cart_list_item_img_wrapper">
              <v-img :src="require(`@/assets/productImage/${item.product.thumbnail}`)" width="100" height="100" />
            </div>
            <div class="div-cart_list_item_detail">
              <div>{{item.product.name}}</div>
              <div>
                <div class="f-small">₩{{item.product.price.toLocaleString("ko-KR")}}</div>
                <div class="f-small">size: {{item.size}}</div>
              </div>
            </div>
            <div class="d-flex mx-16">
              <button class="btn-quantity_control" @click="quantityControl('down', i)">
                <font-awesome-icon icon="fa-solid fa-minus" size="2xs" />
              </button>
              <input class="input-quantity_control" type="number" :value="item.quantity" ref="inputQuantity" @input="quantityControl('input', i)">
              <button class="btn-quantity_control" @click="quantityControl('up', i)">
                <font-awesome-icon icon="fa-solid fa-plus" size="2xs" />
              </button>
            </div>
            <div class="div-cart_list_item_price">₩{{item.totalPrice.toLocaleString("ko-KR")}}</div>
            <button class="btn-cart_list_item_delete" @click="deleteCartItem(i, item.id)">
              <font-awesome-icon icon="fa-solid fa-xmark" />
            </button>
          </div>
          <!-- Cart Item End -->

          <!-- Cart is empty -->
          <div class="div-empty_text_continue_btn_wrapper" align="center" v-if="!productList.length">
            <div class="div-cart_is_empty">Cart is empty</div>
            <router-link class="router-link-continue_browsing" to="/">Continue Browsing</router-link>
          </div>
          <!-- Cart is empty End -->
        </div>

        <!-- Order Summary -->
        <div class="d-flex div-cart_title" style="justify-content: space-between" v-if="productList.length">
          <div>Total</div>
          <div>₩{{totalPrice.toLocaleString("ko-KR")}}</div>
        </div>
        <!-- Order Summary End -->

        <!-- Checkout Btn -->
        <div align="center" v-if="productList.length">
          <v-btn class="btn-checkout" elevation="0">Checkout</v-btn>
        </div>
        <!-- Checkout Btn End -->
      </div>
    </div>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      productList: [],
      totalPrice: 0
    }
  },

  mounted() {
    this.getCartItems();
  },

  methods: {
    getCartItems() {
      this.$axios.get("/cart/get/item/1").then((res) => {
        res.data.forEach(element => {
          this.productList.push({
            id: element.id,
            product: element.product,
            size: element.size,
            quantity: element.quantity,
            totalPrice: element.totalPrice
          });

          this.totalPrice += element.totalPrice;
        });
      });
    },

    quantityControl(type, i) {
      this.totalPrice -= this.productList[i].quantity * this.productList[i].product.price;

      if (type == 'down') {
        this.productList[i].quantity -= 1;
      } else if (type == 'up') {
        this.productList[i].quantity += 1;
      } else {
        this.productList[i].quantity = Number(this.$refs.inputQuantity[i].value);
      }

      this.productList[i].totalPrice = this.productList[i].quantity * this.productList[i].product.price;
      
      this.totalPrice += this.productList[i].quantity * this.productList[i].product.price;
    },

    deleteCartItem(i, id) {
      this.$axios.delete(`/cart/delete/item/${id}`).then((res) => {
        this.totalPrice -= this.productList[i].totalPrice;
        this.$delete(this.productList, i);
      });
    }
  },
}
</script>

<style>
.f-small {
  font-size: 14px;
  color: #777777;
}

.div-cart_section {
  max-width: 700px;
}

.div-cart_title {
  font-size: 20px;
  line-height: 50px;
  display: block;
}

.div-cart_list {
  height: auto;
  border-top: 1px solid #CCCCCC;
  border-bottom: 1px solid #CCCCCC;
}

.div-cart_list_item {
  margin: 20px 0 20px 0;
}

.div-cart_list_item_img_wrapper {
  float: left; margin-right: 10px; border: 1px solid #999999;
}

.div-cart_list_item_detail {
  width: 254px; 
  height: 100px; 
  display:flex; 
  flex-direction: column; 
  justify-content: space-between;
}

.btn-quantity_control {
  height: fit-content;
}

.input-quantity_control {
  width: 20px; 
  height: fit-content; 
  margin: 0 10px 0 10px; 
  text-align: center;
}

.div-cart_list_item_price {
  width: 140px;
}

.btn-cart_list_item_delete {
  height: fit-content; 
}

.btn-checkout {
  background-color: black !important;
  color: white !important;
  border-radius: 0px !important;
  font-size: 17px;
  line-height: 23.8px;
  text-align: center;
  word-spacing: 0px;
  height: 43.8px;
  width: 250px;
  padding: 10px 20px 10px 20px;
  margin-top: 50px;
}

.div-empty_text_continue_btn_wrapper {
  margin: 170px 0 170px 0;
}

.div-cart_is_empty {
  font-size: 22px;
  height: 40px;
}

.router-link-continue_browsing {
  text-decoration: underline;
}

input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>