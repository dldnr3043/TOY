<template>
  <div id="alert">
    <div class="alert alert-dismissible alert-secondary" v-if="active">
      <button type="button" class="btn-close" data-bs-dismiss="alert" v-on:click="closeAlertDialog()"></button>
      <strong>{{title}}</strong>
      <p>{{msg}}</p>
    </div>
  </div>
</template>

<script>


export default {
  name: 'Alert',
  props: {

  },

  data() {
    return {
      active: false,
      title: "",
      msg: ""
    }
  },

  mounted: function() {
    this.$eventBus.$on('openAlertDialog', (payload) => {
      this.active = payload.active;
      this.title = payload.title;
      this.msg = payload.msg;
    }); 
  },

  methods: {
    closeAlertDialog : function() {
      this.active = false;
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style >

</style>
