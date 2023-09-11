<template>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">{{ contact?.name }} {{ contact?.surname }}</h5>
      <h6 class="card-subtitle mb-2">Phone: {{ contact?.phone }}</h6>
      <h6 class="card-subtitle mb-2">Address: {{ contact?.address }}</h6>
      <h6 class="card-subtitle mb-2">Latitude: {{ contact?.lat }}</h6>
      <h6 class="card-subtitle mb-2">Longitude: {{ contact?.lon }}</h6>
      <p class="card-text">
          {{ contact?.other }}
      </p>
      <router-link to="/contacts" class="btn btn-primary">Go back to contact list</router-link>
    </div>
  </div>
</template>

<script lang="ts">
import type {Contact} from "../types/Contact";
import contactService from "../services/contactService";
import { defineComponent, ref } from "vue";
import { useRoute } from "vue-router";

export default defineComponent({
  name: "ShowContactView",
  setup() {
    const route = useRoute();
    const id: string | string[] = route.params.id;
    let contact = ref<Contact>();

    contactService
        .getContactById(id)
        .then((Response) => (contact.value = Response));

    return {
      contact,
    };
  },
});
</script>