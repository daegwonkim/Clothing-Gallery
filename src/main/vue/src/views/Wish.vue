<template>
  <v-container class="pa-16">
    <div class="div-wish_title">My Wishlist</div>
    <div class="div-wish_subtitle">View favorite products you’ve saved to your wishlist.</div>
    <div align="center">
      <!-- No Wish Items -->
      <div class="div-no_wish" v-if="wishItems.length == 0">
        <div class="div-no_wish_detail">You haven’t added any products yet.</div><br>
        <router-link to="/">Start adding products</router-link>
      </div>
      <!-- No Wish Items End -->

      <!-- Wish Items -->
      <ul class="ul-product_list">
        <li class="li-product_list_item" v-for="(item, i) in filteredList" :key="i" @click="showProductDetail(item.id)">
          <div class="div-product_list_item_wrapper" style="width: 185px; height: 185px;">
            <v-hover v-slot="{ hover }">
              <v-img :src="require(`@/assets/productImage/${item.thumbnail}`)">
                <v-fade-transition>
                  <div v-if="hover">
                    <v-img
                      :src="require(`@/assets/productImage/product${item.id}/product${item.id}_img2.webp`)"
                      style="width: 185px; height: 185px;">
                      <button icon class="btn-delete_wish" type="button" @click.stop="deleteWish(item.id, i)">
                        <font-awesome-icon icon="fa-solid fa-xmark" size="sm" style="color: #555555" />
                      </button>
                    </v-img>
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
    <!-- Wish Items End -->

    <!-- Pagination -->
    <div class="text-center" v-if="wishItems.length > 0">
      <v-pagination
        v-model="curPage"
        :length="Math.ceil(wishItems.length / numToShow)"
        :total-visible="7"
        color="black"
        @input="paging"
      ></v-pagination>
    </div>
    <!-- Pagination End -->
  </v-container>
</template>

<script>
export default {
  computed: {
    filteredList() {
      let start = (this.curPage - 1) * this.numToShow
      let end = this.curPage * this.numToShow
      let result = this.wishItems.slice(start, end)

      return result
    }
  },

  data() {
    return {
      wishItems: [],
      curPage: 1,
      numToShow: 5,
    }
  },

  mounted() {
    this.getWishList();
  },

  methods: {
    getWishList() {
      this.$axios.get("/api/wish/get/item/1").then((productIdList) => {
        this.$axios.get(`/api/product/get/wish-list?productId=${productIdList.data}`).then((res) => {
          res.data.forEach(element => {
            this.wishItems.push(element);
          });
        });
      });
    },

    paging(value) {
      this.curPage = value;
    },

    showProductDetail(id) {
      this.$router.push({
        name: "detail",
        query: { productId: id },
      });
    },

    deleteWish(productId, i) {
      this.$axios.delete(`/api/wish/delete/item/${productId}/1`).then((res) => {
        this.$delete(this.wishItems, i);
      });
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

.div-no_wish {
  height: 340px; 
  padding-top: 130px;
}

.div-no_wish_detail {
  font-size: 20px;
}

.div-no_wish > a {
  color: #F2602F !important;
}

.v-pagination {
  margin-top: 30px !important;
}

.btn-delete_wish {
  float: right;
  margin: 5px 5px 0 0;
  background-color: white;
  border-radius: 50%;
  text-align: center;
  width: 15px;
  line-height: 13px;
}
</style>