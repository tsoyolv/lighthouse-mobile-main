package ru.lighthouse.mobile.main.service.building.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WallMaterial {
    BRICK("Кирпичный"),
    MONOLITHIC("Монолитный"),
    STALINKA("Сталинка"),
    PANEL("Панельный"),
    BLOCKY("Блочный"),
    KHRUSHCHEV("Хрущевка"),
    WOODEN("Деревянный");

    private final String fullName;

    public static WallMaterial fromFullName(String fullName) {
        for (WallMaterial wallMaterial : WallMaterial.values()) {
            if (wallMaterial.getFullName().equals(fullName)) {
                return wallMaterial;
            }
        }
        return null;
    }
}
