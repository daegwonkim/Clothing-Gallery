<template>
  <div>
    <v-parallax src="@/assets/mainImage/main.jpg" height="800">
      <div class="div-parallax_text_wrapper">
        <div class="div-parallax_text-01">New CLOTHING Arrivals</div>
        <div class="div-parallax_text-02">SHOP NOW</div>
      </div>
    </v-parallax>

    <!-- New Product Slide -->
    <div class="div-mini_title">NEW ARRIVALS</div>
    <Slider :products="new_arrivals" />
    <!-- New Product Slide End -->

    <br />

    <!-- Shop Categoty Slide -->
    <v-carousel hide-delimiters cycle height="600">
      <v-carousel-item v-for="(image, i) in v_carousel_img" :key="i">
        <v-sheet height="100%">
          <div class="div-carousel_wrapper">
            <v-img class="img-carousel_img" :src="image" />
            <div class="div-carousel_text_wrapper">
              <div class="div-carousel_text-01">{{ v_carousel_text[i] }}</div>
              <div class="div-carousel_text-02">SHOP NOW</div>
            </div>
          </div>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
    <!-- Shop Categoty Slide End -->

    <br />

    <!-- Most Popular Product Slide -->
    <div class="div-mini_title">MOST POPULAR</div>
    <br />
    <v-img src="@/assets/most_popular.jpg" />
    <Slider :products="most_popular" />
    <!-- Most Popular Product Slide End -->
  </div>
</template>

<script>
import Slider from "../components/Slider.vue";

export default {
  components: {
    Slider,
  },

  data() {
    return {
      v_carousel_img: [
        require("@/assets/categoryImage/tops.jpg"),
        require("@/assets/categoryImage/bottoms.jpg"),
        require("@/assets/categoryImage/footwear.jpg"),
        require("@/assets/categoryImage/accessories.jpg"),
      ],
      v_carousel_text: ["TOPS", "BOTTOMS", "FOOTWEAR", "ACCESSORIES"],
      new_arrivals: [],
      most_popular: [],
    };
  },

  mounted() {
    this.getData(this.new_arrivals, "new");
    this.getData(this.most_popular, "most");
  },

  methods: {
    // 신규 및 인기 상품 데이터 불러오기
    getData(need_data, type) {
      this.$axios.get(`/test/${type}`).then((res) => {
        res.data.forEach((element) => {
          need_data.push({
            id: element.id,
            name: element.name,
            price: element.price.toLocaleString("ko-KR"),
            thumbnail: element.thumbnail,
          });
        });
      });
    },
  },
};
</script>

<style>
.div-parallax_text_wrapper {
  position: relative;
  bottom: -200px;
  text-align: center;
}

.div-parallax_text-01 {
  font-size: 34px;
  line-height: 47.6px;
}

.div-parallax_text-02 {
  font-size: 21px;
  font-weight: 700;
  letter-spacing: 0.21px;
  line-height: 27.3px;
}

.div-mini_title {
  font-size: 17px;
  letter-spacing: 1.7px;
  text-align: center;
  padding-top: 30px;
}

.div-carousel_wrapper {
  position: relative;
}

.img-carousel_img {
  filter: brightness(40%);
}

.div-carousel_text_wrapper {
  position: absolute;
  top: 250px;
  width: 100%;
  text-align: center;
  color: #fff;
}

.div-carousel_text-01 {
  font-size: 20px;
  font-weight: 300;
  letter-spacing: 2px;
}

.div-carousel_text-02 {
  font-size: 15px;
  font-weight: 100;
  margin-top: 10px;
  letter-spacing: 7px;
}
</style>