<template>
  <div>
    <h2 class="h2-no_items_matched" v-if="noItems">No items matched your search criteria. Try widening your search.</h2>

    <!-- Product List -->
    <div>
      <ul class="ul-product_list">
        <li class="li-product_list_item" v-for="i in productToShow" :key="i" @click="showProductDetail(product[i - 1].id)">
          <div class="div-product_list_item_wrapper" :style="{width: `${itemWidth}px`, height: `${itemHeight}px`}">
            <v-hover v-slot="{ hover }">
              <v-img :src="require(`@/assets/productImage/${product[i - 1].thumbnail}`)">
                <v-fade-transition>
                  <div v-if="hover">
                    <v-img
                      :src="require(`@/assets/productImage/product${product[i - 1].id}/product${product[i - 1].id}_img2.webp`)"
                      :style="{width: `${itemWidth}px`, height: `${itemHeight}px`}" />
                  </div>
                </v-fade-transition>
              </v-img>
            </v-hover>
            <div align="left">
              <div class="div-product_name">{{product[i - 1].name}}</div>
              <div class="d-flex div-product_price_wrapper">
                <div class="div-product_price" :class="{ line_through: category == 'sale' }">₩{{product[i - 1].price}}</div>
                <div v-if="category == 'sale'" class="div-product_discount_price">₩{{product[i - 1].discountPrice}}</div>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <!-- Product List End -->

    <!-- Load More -->
    <div v-if="productToShow < totalProduct" class="div-load_more_btn_wrapper" align="center">
      <v-btn class="btn-load_more" elevation="0" @click="loadMore">Load More</v-btn>
    </div>
    <!-- Load More End -->

  </div>
</template>

<script>
import { eventBus } from "@/main.js"

export default {
  props: ["category", "itemWidth", "itemHeight"],

  data() {
    return {
      data: [],
      product: [],
      totalProduct: 0,
      productToShow: 0,
      loadDataCount: 16,
      noItems: false,
    }
  },

  mounted() {
    this.getData();
    
    eventBus.$on("setDesign", (curBrand, curLow, curHigh) => {
      this.printBy(curBrand, curLow, curHigh);
    });
  },

  beforeDestroy() {
    eventBus.$off("setDesign");
  },

  methods: {
    // 상품 데이터 가져오기
    getData() {
      this.$axios.get(`/product/get/list/item/${this.category}`).then((res) => {
        res.data.forEach((element) => {
          this.data.push({
            id: element.id,
            name: element.name,
            price: element.price.toLocaleString("ko-KR"),
            thumbnail: element.thumbnail,
            brand: element.brand,
            discountPrice: element.discountPrice.toLocaleString("ko-KR"),
            registerDate: element.registerDate
          });
        });

        this.product = this.data;
        this.totalProduct = this.product.length;
        this.productToShow = Math.min(this.loadDataCount, this.totalProduct);

        // 브랜드 리스트 설정
        let brands = new Set();
        this.product.forEach(element => {
          brands.add(element.brand);
        });

        // List.vue -> Designer.vue
        eventBus.$emit("setBrands", brands);
      });
    },

    // 상품 데이터 추가 로드
    loadMore() {
      this.productToShow += Math.min(this.loadDataCount, this.totalProduct - this.productToShow);
    },

    // 상품 리스트 정렬
    sortBy(option) {
      if (option == "Newest") {  // Newest
        this.product.sort((a, b) => {
          return new Date(b.registerDate).getTime() - new Date(a.registerDate).getTime();
        });
      } else if (option == "Price (low to high)") { // Price (low to high)
        this.product.sort((a, b) => {
          return Number(a.price.replace(',', '')) - Number(b.price.replace(',', ''));
        });
      } else if (option == "Price (high to low)") { // Price (high to low)
        this.product.sort((a, b) => {
          return Number(b.price.replace(',', '')) - Number(a.price.replace(',', ''));
        });
      } else if (option == "Name A-Z") { // Name A-Z
        this.product.sort((a, b) => {
          return a.name.localeCompare(b.name);
        });
      } else if (option == "Name Z-A") { // Name Z-A
        this.product.sort((a, b) => {
          return b.name.localeCompare(a.name);
        });
      }
    },

    printBy(brand, low, high) {
      let temp = [];

      // 브랜드별 분류
      if (brand == "All") {
        temp = this.data;
      } else {
        this.data.forEach(element => {
          if (element.brand == brand) {
            temp.push({
              id: element.id,
              name: element.name,
              price: element.price,
              thumbnail: element.thumbnail,
              brand: element.brand,
              registerDate: element.registerDate
            })
          }
        });
      }

      this.product = []

      // 가격별 분류
      temp.forEach(element => {
        let price = Number(element.price.replace(',', ''));
        if (low <= price && price <= high) {
          this.product.push({
            id: element.id,
            name: element.name,
            price: element.price,
            thumbnail: element.thumbnail,
            brand: element.brand,
            registerDate: element.registerDate
          })
        }
      })

      this.totalProduct = this.product.length;
      this.productToShow = Math.min(this.loadDataCount, this.totalProduct);

      if (this.totalProduct == 0) {
        this.noItems = true;
      } else {
        this.noItems = false;
      }
    },

    showProductDetail(id) {
      this.$router.push({
        name: "detail",
        query: { productId: id },
      });
    }
  },
};
</script>

<style>
.h2-no_items_matched {
  font-size: 24px;
  line-height: 33.6px;
  text-align: center;
  font-weight: 100;
  margin: 100px 0 0 0;
}

.ul-product_list {
  list-style: none;
}

.li-product_list_item {
  float: left;
  margin: 0 20px 80px 0;
  cursor: pointer;
}

.div-product_list_item_wrapper {
  width: 345px;
  height: 345px;
}

.div-product_name {
  font-size: 17px;
  line-height: 23.8px;
  margin-top: 5px;
  display: block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.div-product_price_wrapper {
  font-size: 16px;
  line-height: 16px;
  color: #666666;
  margin-top: 5px;
}

.div-product_discount_price {
  margin-left: 10px;
}

.line_through {
  text-decoration: line-through;
}

.div-load_more_btn_wrapper {
  margin: 40px 0 40px 0;
  clear: both;
}

.btn-load_more {
  background-color: black !important;
  color: white !important;
  border-radius: 0px !important;
  font-size: 17px;
  line-height: 23.8px;
  text-align: center;
  word-spacing: 0px;
  height: 43.8px;
  width: 316px;
  padding: 10px 20px 10px 20px;
}
</style>