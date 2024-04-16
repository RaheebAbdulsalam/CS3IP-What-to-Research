package com.cs3ip.whattoresearch;

import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.model.ShortList;
import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import com.cs3ip.whattoresearch.repository.ShortListRepository;
import com.cs3ip.whattoresearch.repository.UserRepository;
import com.cs3ip.whattoresearch.service.ShortListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShortlistTest {

    @Mock
    private ShortListRepository shortListRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ShortListService shortListService;

    // Test add to shortList
    @Test
    public void testAddToShortList() {
        User user = new User();
        user.setId(1);

        Project project = new Project();
        project.setId(1);
        when(projectRepository.findById(1)).thenReturn(java.util.Optional.of(project));

        shortListService.addToShortList(user, project.getId());

        ShortList shortListProject = new ShortList();
        shortListProject.setUserId(user.getId());
        shortListProject.setProject(project);

        assertNotNull(shortListProject);
        assertEquals(user.getId(), shortListProject.getUserId());
        assertEquals(project.getId(), shortListProject.getProject().getId());
    }

}
