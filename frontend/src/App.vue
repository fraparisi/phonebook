<script setup lang="ts">
import {RouterView} from 'vue-router';
</script>

<template>
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <div class="navbar-nav mr-auto">
        <li class="nav-item"  v-if="currentUser">
          <a class="nav-link" @click.prevent="logOut">
            Logout
          </a>
        </li>
      </div>
    </nav>
    <div class="container">
      <RouterView/>
    </div>
</template>

<script lang="ts">
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/');
    }
  }
};
</script>