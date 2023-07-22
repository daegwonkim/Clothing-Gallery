<template>
  <v-sheet class="mx-auto">
    <v-slide-group class="pa-4" selected-class="bg-success">
      <v-slide-group-item class="v-slide-group-item__slider" v-for="(product, i) in products" :key="i" @click="showProductDetail(product.id)">
        <v-card
          color="grey-lighten-1"
          :class="['ma-4']"
          height="350"
          width="313.5"
          elevation="0"
        >
          <v-hover v-slot="{ hover }">
            <v-img :src="require(`@/assets/productImage/${product.thumbnail}`)">
              <v-fade-transition>
                <div v-if="hover">
                  <v-img
                    :src="require(`@/assets/productImage/product${product.id}/product${product.id}_img2.webp`)"
                    height="313.5" />
                </div>
              </v-fade-transition>
            </v-img>
          </v-hover>
          <div>
            <div class="div-slide_text-01">{{ product.name }}</div>
            <div class="div-slide_text-02">₩{{ product.price }}</div>
          </div>
        </v-card>
      </v-slide-group-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
export default {
  name: "slider",

  props: ["products"],

  methods: {
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
.v-slide-group-item__slider {
  cursor: pointer;
}

.div-slide_text-01 {
  font-size: 17px;
  line-height: 23.8px;
  margin-top: 5px;
  display: block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.div-slide_text-02 {
  font-size: 16px;
  line-height: 16px;
  color: #666666;
  margin-top: 5px;
}
</style>