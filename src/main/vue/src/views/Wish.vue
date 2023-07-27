<template>
  <v-container class="pa-16">
    <div class="div-wish_title">My Wishlist</div>
    <div class="div-wish_subtitle">View favorite products you’ve saved to your wishlist.</div>
    <div align="center">
      <ul class="ul-product_list">
        <li class="li-product_list_item" v-for="(item, i) in filteredList" :key="i" @click="showProductDetail(item.id)">
          <div class="div-product_list_item_wrapper" style="width: 185px; height: 185px;">
            <v-hover v-slot="{ hover }">
              <v-img :src="require(`@/assets/productImage/${item.thumbnail}`)">
                <v-fade-transition>
                  <div v-if="hover">
                    <v-img
                      :src="require(`@/assets/productImage/product${item.id}/product${item.id}_img2.webp`)"
                      style="width: 185px; height: 185px;" />
                  </div>
                </v-fade-transition>
              </v-img>
            </v-hover>
            <div align="left">
              <div class="div-product_name">{{item.name}}</div>
              <div class="d-flex div-product_price_wrapper">
                <div class="div-product_price">₩{{item.price.toLocaleString("ko-KR")}}</div>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>

    <div class="text-center">
      <v-pagination
        v-model="curPage"
        :length="Math.ceil(wishItems.length / numToShow)"
        :total-visible="7"
        navigation-color="white"
        @input="paging"
      ></v-pagination>
    </div>
  </v-container>
</template>

<script>
export default {
  computed: {
    filteredList() {
      console.log(this.wishItems);
      const start = (this.curPage - 1) * this.numToShow
      const end = this.curPage * this.numToShow
      const result = this.wishItems.slice(start, end)
      return result
    }
  },

  data() {
    return {
      wishItems: [],
      curPage: 1,
      numToShow: 10,
    }
  },

  mounted() {
    this.getWishList();
  },

  methods: {
    getWishList() {
      this.$axios.get("/wish/get/item/1").then((productIdList) => {
        this.$axios.get(`/product/get/wish-list?productId=${productIdList.data}`).then((res) => {
          res.data.forEach(element => {
            this.wishItems.push(element);
          });
        });
      });
    },

    paging(value) {
      this.curPage = value;
    }
  },
}
</script>

<style>
.div-wish_title {
  font-size: 27px;
  line-height: 50px;
  display: block;
  margin-left: 25px;
}

.div-wish_subtitle {
  margin: 0 0 25px 25px;
  padding-bottom: 25px;
}

.v-pagination {
  margin-top: 30px !important;
}

.v-pagination__item.v-pagination__item--active {
  background-color: black !important;
  color: white !important;
}
</style>