<template>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" id="name" class="form-control" v-model="editedContact.name" required />
      </div>
      <div class="form-group">
        <label for="surname">Surname</label>
        <input type="text" id="surname" class="form-control" v-model="editedContact.surname" required />
      </div>
      <div class="form-group">
        <label for="address">Address</label>
        <input type="text" id="address" class="form-control" v-model="editedContact.address" required />
      </div>
      <div class="form-group">
        <label for="phone">Phone</label>
        <input type="tel" id="phone" class="form-control" v-model="editedContact.phone" required />
      </div>
      <div class="form-group">
        <label for="lat">Latitude</label>
        <input type="text" id="lat" class="form-control" v-model="editedContact.lat" required />
      </div>
      <div class="form-group">
        <label for="lon">Longitude</label>
        <input type="text" id="lon" class="form-control" v-model="editedContact.lon" required />
      </div>
      <div class="form-group">
        <label for="bio">Other info</label>
        <textarea id="bio" class="form-control" v-model="editedContact.other" required></textarea>
      </div>
      <div class="form-actions">
        <button type="submit" class="btn btn-primary">Save Changes</button>
        <button type="button" class="btn btn-primary" @click="cancelEdit">Cancel</button>
      </div>
    </form>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import type {Contact} from "../types/Contact";
import contactService from "../services/contactService";
import { useRoute, useRouter } from "vue-router";

export default defineComponent({
  name: "EditContactView",
  setup() {
    const editedContact = ref<Contact>({
      id:0,
      name: "",
      surname: "",
      address: "",
      phone: "",
      lat: "",
      lon: "",
      other: "",
    });
    const route = useRoute();
    const router = useRouter();
    let id = route.params.id;
    contactService.getContactById(id).then((response) => {
      editedContact.value = response;
    });

    const submitForm = () => {
      contactService.edit(id, editedContact.value).then(() => {
        router.push({ name: "contacts" });
      });
    };
    const cancelEdit = () => {
      router.push({ name: "contacts" });
    };

    return {
      editedContact,
      cancelEdit,
      submitForm,
    };
  },
});
</script>