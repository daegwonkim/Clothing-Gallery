<template>
  <div class="div-container">

    <!-- Select Box -->
    <div class="div-select_parent">
      <div class="div-select_child">
        <v-select
          class="select-sort"
          label="Sort by"
          :items="sortOptions"
          @change="requestSort"
          outlined
          dense
          :menu-props="{ bottom: true, offsetY: true }"
        ></v-select>
      </div>
    </div>
    <!-- Select Box End -->

    <!-- Product List -->
    <List ref="listVue" :category="category" :itemWidth="345" :itemHeight="345" />
    <!-- Product List -->

  </div>
</template>

<script>
import List from "../components/List.vue";

export default {
  components: {
    List,
  },

  data() {
    return {
      category: "all",
      sortOptions: [
        'Newest',
        'Price (low to high)',
        'Price (high to low)',
        'Name A-Z',
        'Name Z-A'
      ]
    }
  },

  methods: {
    requestSort(option) {
      this.$refs.listVue.sortBy(option);
    }
  },
};
</script>

<style>
.div-container {
  padding: 0 15px 0 15px;
}

.div-select_parent {
  display: flex;
  justify-content: right;
  margin: 20px 25px 20px 0;
}

.div-select_child {
  width: 200px;
  height: 40px;
}

.select-sort {
  border-radius: 0px !important;
}

.select-sort input {
  cursor: pointer;
}

.my-app.v-application .primary--text {
  color: black !important;
}

.v-text-field--outlined fieldset {
  border: black 1px solid !important;
}
</style>