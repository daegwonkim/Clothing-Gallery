<template>
  <div class="ml-2">
    <v-list class="list-designer" :active="brands">
      <div class="div-list_title">DESIGNERS</div>

      <!-- Brand Category -->
      <v-list-group :ripple="false" value="brands">
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title class="list-item_title">Category</v-list-item-title>
          </v-list-item-content>
        </template>

        <v-list-item v-for="(brand, index) in brands" :key="index">
          <v-list-item-content>
            <v-list-item-title class="list-subitem_title" :class="{active: brand.isActive}" @click="setBrand(index)">{{brand.name}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-group>
      <!-- Brand Category End -->

      <!-- Set Price -->
      <v-list-group :ripple="false">
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title class="list-item_title">Price</v-list-item-title>
          </v-list-item-content>
        </template>

        <v-list-item>
          <v-list-item-content>
            <v-range-slider
              color="black"
              track-color="gray"
              track-fill-color="black"
              v-model="priceRange"
              min="15000"
              max="1000000"
              step="10000"
              @change="setPriceRange"
            />

            <div class="div-price_range">
              <div
                v-for="(price, i) in priceRange"
                :key="i"
                :model-value="price"
                @change="$set(priceRange, i, $event)"
                v-text="`₩${price.toLocaleString('ko-KR')}`"
              />
            </div>
          </v-list-item-content>
        </v-list-item>
      </v-list-group>
      <!-- Set Price End -->

      <!-- Clear Filter Btn -->
      <button
        class="btn-clear_fillters"
        v-if="curBrand != 'All' || curLow != 15000 || curHigh != 1000000"
        @click="clearFilter"
      >Clear Filters X</button>
      <!-- Clear Filter Btn End -->

    </v-list>
  </div>
</template>

<script>
import { eventBus } from "@/main.js";

export default {
  data() {
    return {
      brands: [{name: "All", isActive: true}],
      curBrand: "All",
      curBrandIndex: 0,
      priceRange: [15000, 1000000], 
      curLow: 15000,
      curHigh: 1000000,
    }
  },

  mounted() {
    eventBus.$on("setBrands", (brands) => {
      brands.forEach(element => {
        this.brands.push({
          name: element,
          isActive: false
        })
      });
    });
  },

  beforeDestroy() {
    eventBus.$off("setBrands");
  },

  methods: {
    setPriceRange(event) {
      this.curLow = event[0];
      this.curHigh = event[1];

      // Designer.vue -> List.vue
      eventBus.$emit("setDesign", this.curBrand, this.curLow, this.curHigh);
    },

    setBrand(index) {
      this.brands[this.curBrandIndex].isActive = false;
      this.brands[index].isActive = true;
      this.curBrand = this.brands[index].name;
      this.curBrandIndex = index;

      // Designer.vue -> List.vue
      eventBus.$emit("setDesign", this.curBrand, this.curLow, this.curHigh);
    },

    clearFilter() {
      this.$set(this.priceRange, 0, 15000);
      this.$set(this.priceRange, 1, 1000000);
      this.brands[this.curBrandIndex].isActive = false;
      this.brands[0].isActive = true;
      this.curBrand = "All";
      this.curBrandIndex = 0
      this.curLow = 15000;
      this.curHigh = 1000000;

      // Designer.vue -> List.vue
      eventBus.$emit("setDesign", this.curBrand, this.curLow, this.curHigh);
    },
  }
}
</script>

<style>
.list-designer {
  margin: 33px 0 0 0;
  width: 228px;
}

.div-list_title {
  font-size: 26px;
  line-height: 31.2px;
  margin: 0 0 10px 15px;
  height: 52px;
  border-bottom: solid 1px #bbb;
}

.list-item_title {
  padding: 15px 0 15px 0;
}

.list-subitem_title {
  font-size: 14px !important;
  cursor: pointer;
}

.active {
  font-weight: bold;
}

.list-subitem_title:hover {
  color: #aaa;
}

.v-list-item {
  min-height: 25px !important;
}

.v-list-item__content {
  padding: 0;
}

.v-list-group__header::before {
  content: none;
}

.v-slider {
  cursor: pointer;
  margin: 0 8px -20px 8px;
}

.v-slider__thumb::before {
  content: none !important;
}

.div-price_range {
  display: flex;
  justify-content: space-between;
}

.btn-clear_fillters {
  font-size: 14px;
  line-height: 19.6px;
  text-align: center;
  margin: 20px 0 0 15.5px;
}
</style>