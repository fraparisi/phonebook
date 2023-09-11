<template>
  <div class="home">
    <h1>
      Contacts
    </h1>
    <div class="row">
      <div class="col-xs-12 offset-10">
        <router-link to="/add/contact" class="btn" tag="button">Add Contact</router-link>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-12 ">
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="name">Keyword</label>
          <input
              type="text"
              id="keyword"
              class="form-control"
              v-model="search.keyword"
          />
        </div>
        <div class="form-group">
          <label for="surname">Phone</label>
          <input
              type="text"
              id="phone"
              class="form-control"
              v-model="search.phone"
          />
        </div>
        <div class="form-actions">
          <button type="submit" class="btn btn-primary">Search</button>
        </div>
      </form>
      </div>
    </div>
    <div v-for="(contact, index) in contacts" :key="index">
      <contact-card :contact="contact" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import ContactCard from "../components/ContactCard.vue";
import type {Contact} from "../types/Contact";
import contactService from "../services/contactService";
import type {Search} from "@/types/Search";

export default defineComponent({
  name: "ListContactView",
  components: { ContactCard },

  setup() {
    let contacts = ref<Contact[]>([]);
    let search = ref<Search>({
      keyword: "",
      phone:""
    });
    contactService.getAllContacts().then((response) => {
      contacts.value = response;
    });

    const submitForm = () => {
      contactService.search(search.value).then((response) => {
        contacts.value = response;
      });
    };

    return {
      contacts,
      search,
      submitForm
    };
  },
});
</script>
