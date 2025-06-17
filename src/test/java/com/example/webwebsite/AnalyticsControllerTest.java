package com.example.webwebsite;

import com.example.webwebsite.controller.AnalyticsController;
import com.example.webwebsite.dto.CourseProgressDTO;
import com.example.webwebsite.dto.UserCourseViewDTO;
import com.example.webwebsite.pojo.CourseViewLog;
import com.example.webwebsite.pojo.Result;
import com.example.webwebsite.service.AnalyticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


/**
 * @author superG
 * @date 2025/6/12
 */





public class AnalyticsControllerTest {

    @Mock
    private AnalyticsService analyticsService;

    @InjectMocks
    private AnalyticsController analyticsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Tests for getPopularCourses




    @Test
    public void testGetUserTotalViewDuration_NoLogs() {
        when(analyticsService.getUserViewLogs(1)).thenReturn(new ArrayList<>());

        Result result = analyticsController.getUserTotalViewDuration(1);
        assertEquals(Result.SUCCESS_CODE, result.getCode());
        assertEquals(0, result.getData());
    }



    // Tests for getCourseProgress
    @Test
    public void testGetCourseProgress_WithProgress() {
        CourseProgressDTO progress = new CourseProgressDTO(); // Assuming DTO has progress field

        when(analyticsService.getCourseProgress(1L, 1)).thenReturn(progress);

        Result result = analyticsController.getCourseProgress(1L, 1);
        assertEquals(Result.SUCCESS_CODE, result.getCode());
        assertEquals(progress, result.getData());
    }

    @Test
    public void testGetCourseProgress_NoProgress() {
        CourseProgressDTO progress = new CourseProgressDTO();

        when(analyticsService.getCourseProgress(1L, 1)).thenReturn(progress);

        Result result = analyticsController.getCourseProgress(1L, 1);
        assertEquals(Result.SUCCESS_CODE, result.getCode());
        assertEquals(progress, result.getData());
    }



    @Test
    public void testGetUserTotalViewData_NoLogs() {
        when(analyticsService.getUserViewLogs(1)).thenReturn(new ArrayList<>());

        Result result = analyticsController.getUserTotalViewData(1);
        assertEquals(Result.SUCCESS_CODE, result.getCode());
        assertTrue(((List<?>) result.getData()).isEmpty());
    }

    // Tests for getUserCourseViewData
    @Test
    public void testGetUserCourseViewData_WithData() {
        List<UserCourseViewDTO> viewData = List.of(
                new UserCourseViewDTO(), // Assuming DTO structure
                new UserCourseViewDTO()
        );
        when(analyticsService.getUserCourseViewData(1)).thenReturn(viewData);

        Result result = analyticsController.getUserCourseViewData(1);
        assertEquals(Result.SUCCESS_CODE, result.getCode());
        assertEquals(viewData, result.getData());
    }

    @Test
    public void testGetUserCourseViewData_NoData() {
        when(analyticsService.getUserCourseViewData(1)).thenReturn(new ArrayList<>());

        Result result = analyticsController.getUserCourseViewData(1);
        assertEquals(Result.SUCCESS_CODE, result.getCode());
        assertTrue(((List<?>) result.getData()).isEmpty());
    }
}
