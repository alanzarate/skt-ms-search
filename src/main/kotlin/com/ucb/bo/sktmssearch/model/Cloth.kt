package com.ucb.bo.sktmssearch.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "cloth")
class Cloth(
    var name: String,
    var description: String,
    var available: Boolean,
    @Column(name="user_id")
    var userId: Long,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cloth_id")
    var clothId: Long = 0
){
    constructor() : this("", "", false, 0)
}