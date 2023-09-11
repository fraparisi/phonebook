package com.demo.phonebook.repository;

import com.demo.phonebook.entity.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    @Query(value = "SELECT * FROM contacts WHERE name like %:keyword% OR surname LIKE %:keyword% OR address LIKE %:keyword% OR other LIKE %:keyword% OR lat LIKE %:keyword% OR lon LIKE %:keyword%", nativeQuery = true)
    public List<Contact> search(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM contacts WHERE (name like %:keyword% OR surname LIKE %:keyword% OR address LIKE %:keyword% OR other LIKE %:keyword% OR lat LIKE %:keyword% OR lon LIKE %:keyword%) and phone = :phone", nativeQuery = true)
    public List<Contact> searchWithPhone(@Param("keyword") String keyword, @Param("phone") String phone);

    List<Contact> findByPhone(String phone);
}