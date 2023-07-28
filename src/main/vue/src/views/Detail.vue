<template>
  <v-container>
    <div class="d-flex div-product_path">
      <div>
        <span>
          <router-link :to="'/'">Home</router-link>
        </span>
        <span>/</span>
        <span>{{product.name}}</span>
      </div>

      <div>
        <span class="span-prev_next_btn" :class="{btn_disabled: prevProductId == -1}" @click="showPrevOrNextProduct('prev')">
          <font-awesome-icon icon="fa-solid fa-chevron-left" size="sm" /> Prev
        </span>
        <span>|</span>
        <span class="span-prev_next_btn" :class="{btn_disabled: nextProductId == -1}" @click="showPrevOrNextProduct('next')">
          Next <font-awesome-icon icon="fa-solid fa-chevron-right" size="sm" />
        </span>
      </div>
    </div>

    <div class="d-flex div-product_img_info_wrapper">
      <!-- Product Images -->
      <div>
        <div class="div-product_img_list_wrapper" v-for="(productImage, i) in productImages" :key="i">
          <v-img :class="{active__detail: productImage.isActive}" :src="require(`@/assets/productImage/${productImage.path}`)" @click="switchingImage(i)" />
        </div>
      </div>
      <div class="div-product_img_wrapper">
        <v-img :src="require(`@/assets/productImage/${productImages[curShowImageIndex].path}`)"/>
      </div>
      <!-- Product Images End -->

      <div class="div-product_info">
        <!-- Product Info -->
        <div class="div-detail_product_name">{{product.name}}</div>
        <div class="div-detail_product_price">₩{{product.price.toLocaleString("ko-KR")}}</div>
        <div>{{product.description}}</div>
        <div :class="{read_more: readMoreActive}">
          Details:
          <ul>
            <li v-for="(productFeature, i) in productFeatures" :key="i">{{productFeature}}</li>
          </ul>
        </div>
        <div class="div-read_more_btn" @click="readMore">{{readMoreText}}</div>
        <!-- Product Info End -->

        <!-- Select Size -->
        <div class="mb-12">
          <div>Size</div>
          <v-tooltip left color="error" v-model="showSizeTooltip">
            <template v-slot:activator="{ attrs }">
              <v-select
                v-model="selectSize"
                class="select-select_size"
                label="Select size"
                :items="productSizes"
                item-text="size"
                item-disabled="disable"
                outlined
                dense
                :menu-props="{ bottom: true, offsetY: true }"
                hide-details
                v-bind="attrs"
              ></v-select>
            </template>
            <span>Select size</span>
          </v-tooltip>
        </div>
        <!-- Select Size End -->

        <!-- Input Quantity -->
        <div class="mt-n6">
          <div>Quantity</div>
          <v-tooltip left color="error" v-model="showQuantityTooltip">
            <template v-slot:activator="{ attrs }">
              <input
                v-model="inputQuantity"
                class="input-quantity__detail"
                type="number"
                min="1"
                v-bind="attrs"
              >
            </template>
            <span>Enter a minimum amount of 1</span>
          </v-tooltip>
        </div>
        <!-- Input Quantity End -->

        <div class="d-flex mt-n4 div-add_to_btn_wrapper">
          <v-tooltip left color="success" v-model="showSuccessTooltip">
            <template v-slot:activator="{ attrs }">
              <v-btn 
                class="btn-add_to_cart" 
                elevation="0" 
                min-width="227" 
                min-height="43" 
                @click="addToCart"
                v-bind="attrs"
              >Add to Cart</v-btn>
            </template>
            <span>Successfully add!</span>
          </v-tooltip>
          <v-btn 
            class="btn-add_to_wish" 
            elevation="0" 
            max-width="43"
            min-width="43" 
            min-height="43" 
            outlined
            @click="toggleWish"
           >
            <font-awesome-icon v-if="!isWish" icon="fa-regular fa-heart" size="xl" style="color: #888888;" />
            <font-awesome-icon v-if="isWish" icon="fa-solid fa-heart" size="xl" style="color: #888888;" />
          </v-btn>
        </div>
      </div> 
    </div>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      productId: null,
      prevProductId: null,
      nextProductId: null,
      product: null,
      productSizes: [],
      productImages: [],
      productFeatures: [],
      curShowImageIndex: 0,

      inputQuantity: 1,
      selectSize: "",

      readMoreActive: true,
      readMoreText: "Read More",

      showSizeTooltip: false,
      showQuantityTooltip: false,
      showSuccessTooltip: false,

      isWish: false,
    };
  },

  mounted() {
    this.productId = this.$route.query.productId;

    this.getProductDetail();
  },

  methods: {
    getProductDetail() {
      this.$axios.get(`/product/get/detail/${this.productId}`).then((res) => {
        this.product = res.data.product;
        this.prevProductId = res.data.prevProduct;
        this.nextProductId = res.data.nextProduct;
      });

      this.$axios.get(`/size/get/detail/${this.productId}`).then((res) => {
        res.data.forEach(element => {
          this.productSizes.push({
            size: element.size,
            stock: element.stock,
            disable: element.stock < 1 ? true : false
          });
        });
      });

      this.$axios.get(`/image/get/detail/${this.productId}`).then((res) => {
        res.data.forEach(element => {
          this.productImages.push({
            path: element,
            isActive: false
          });
        });
      });

      this.$axios.get(`/feature/get/detail/${this.productId}`).then((res) => {
        res.data.forEach(element => {
          this.productFeatures.push(element);
        });
      });

      this.$axios.get(`/wish/get/${this.productId}/state/1`).then((res) => {
        this.isWish = res.data;
      });
    },

    switchingImage(i) {
      this.productImages[this.curShowImageIndex].isActive = false;

      this.curShowImageIndex = i;
      this.productImages[this.curShowImageIndex].isActive = true;
    },

    readMore() {
      this.readMoreActive = !this.readMoreActive;
      this.readMoreText = this.readMoreActive ? "Read More" : "Read Less";
    },

    showPrevOrNextProduct(type) {
      let showProductId;

      if(type == "prev") {
        showProductId = this.prevProductId;
      } else {
        showProductId = this.nextProductId;
      }
      
      this.$router.push({
        name: "detail",
        query: { productId: showProductId },
      });
    },

    addToCart() {
      if(this.selectSize == "" || this.inputQuantity < 1) {
        if(this.selectSize == "") {
          this.showSizeTooltip = true;

          setTimeout(() => {
            this.showSizeTooltip = false;
          }, 5000);
        }
        if(this.inputQuantity < 1) {
          this.showQuantityTooltip = true;

          setTimeout(() => {
            this.showQuantityTooltip = false;
          }, 5000);
        }
        return;
      }

      this.$axios.get(`/product/get/${this.productId}`).then((product) => {
        this.$axios.post("/cart/add", {customerId: 1, product: product.data, size: this.selectSize, quantity: this.inputQuantity}).then((res) => {
          this.showSuccessTooltip = true;

          setTimeout(() => {
            this.showSuccessTooltip = false;
          }, 5000);
        });
      })
    },

    toggleWish() {
      if(this.isWish) {
        this.$axios.delete(`/wish/delete/item/${this.productId}/1`).then((res) => {
          this.isWish = false;
        });
      } else {
        this.$axios.get("/customer/get/1").then((customer) => {
          this.$axios.get(`/product/get/${this.productId}`).then((product) => {
            this.$axios.post("/wish/add", {customer: customer.data, product: product.data}).then((res) => {
              this.isWish = true;
            });
          });
        });
      }
    }
  },
};
</script>

<style>
.div-product_path {
  margin: 50px 0 0 10px;
  justify-content: space-between;
}

.div-product_path span {
  padding-right: 5px;
}

.div-product_img_info_wrapper {
  justify-content: space-between;
  margin-top: 50px;
}

.div-product_img_list_wrapper {
  border: 1px solid black;
  margin: 10px;
  overflow: hidden;
}

.div-product_img_list_wrapper > .v-image {
  width: 50px;
  height: 50px;
  cursor: pointer;
}

.span-prev_next_btn {
  font-size: 18px;
  cursor: pointer;
  margin: 0 3px 0 3px;
}

.btn_disabled {
  pointer-events: none;
  color: #888888;
}

.active__detail {
  border: 1.5px solid black;
}

.div-product_img_wrapper {
  overflow: hidden;
  margin-top: 10px;
}

.div-product_img_wrapper .v-image {
  width: 500px;
  height: 560px;
}

.div-product_info {
  width: 280px;
}

.div-product_info > div {
  margin-bottom: 20px;
}

.div-detail_product_name {
  font-size: 30px;
  line-height: 36px;
  width: 280px;
}

.div-detail_product_price {
  font-size: 20px;
  line-height: 25px;
  color: #777777;
}

.div-read_more_btn {
  margin-top: -15px;
  font-size: 14px;
  text-decoration: underline;
  cursor: pointer;
  width: fit-content;
}

.read_more {
  display: none;
}

.select-select_size {
  border-radius: 0px;
  width: 280px;
}

.input-quantity__detail {
  border-radius: 0px;
  width: 80px;
  height: 40px;
  padding: 0 1px 0 12px;
  border: 1px solid black;
  margin-bottom: 30px;
}

.div-add_to_btn_wrapper > button {
  margin-right: 10px;
}

.btn-add_to_cart {
  background-color: black !important;
  color: white !important;
  border-radius: 0px !important;
  font-size: 17px;
  text-align: center;
}

.btn-add_to_wish {
  border-radius: 0px;
  text-align: center;
  border-color: #808080;
}
</style>