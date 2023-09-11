<template>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="name">Name</label>
        <input
            type="text"
            id="name"
            class="form-control"
            v-model="contactToBeSaved.name"
            required
        />
      </div>
      <div class="form-group">
        <label for="surname">Surname</label>
        <input
            type="text"
            id="surname"
            class="form-control"
            v-model="contactToBeSaved.surname"
            required
        />
      </div>
      <div class="form-group">
        <label for="address">Address</label>
        <input
            type="text"
            id="address"
            class="form-control"
            v-model="contactToBeSaved.address"
            required
        />
      </div>
      <div class="form-group">
        <label for="phone">Phone</label>
        <input
            type="tel"
            id="phone"
            class="form-control"
            v-model="contactToBeSaved.phone"
            required
        />
      </div>
      <div class="form-group">
        <label for="lat">Latitude</label>
        <input
            type="text"
            id="lat"
            class="form-control"
            v-model="contactToBeSaved.lat"
            required
        />
      </div>
      <div class="form-group">
        <label for="lon">Longitude</label>
        <input
            type="text"
            id="lon"
            class="form-control"
            v-model="contactToBeSaved.lon"
            required
        />
      </div>
      <div class="form-group">
        <label for="other">Other info</label>
        <textarea id="other" class="form-control" v-model="contactToBeSaved.other" required></textarea>
      </div>
      <div class="form-actions">
        <button type="submit" class="btn btn-primary">Save Contact</button>
        <button type="button" class="btn btn-primary" @click="cancelEdit">Cancel</button>
      </div>
    </form>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import type {Contact} from "../types/Contact";
import contactService from "../services/contactService";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "AddContactView",
  setup() {
    const contactToBeSaved = ref<Contact>({
      id: 0,
      name: "",
      surname: "",
      address: "",
      phone: "",
      lat: "",
      lon: "",
      other: "",
    });
    const router = useRouter();

    const submitForm = () => {
      contactService.save(contactToBeSaved.value).then(() => {
        router.push({ name: "contacts" });
      });

    };
    const cancelEdit = () => {
      router.push({ name: "contacts" });
    };

    return {
      contactToBeSaved,
      cancelEdit,
      submitForm,
    };
  },
});
</script>


