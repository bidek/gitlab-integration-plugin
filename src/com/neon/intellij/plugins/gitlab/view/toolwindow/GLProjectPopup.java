package com.neon.intellij.plugins.gitlab.view.toolwindow;

import com.intellij.icons.AllIcons;
import com.neon.intellij.plugins.gitlab.controller.GLIController;
import com.neon.intellij.plugins.gitlab.model.intellij.GLProjectNode;
import org.gitlab.api.models.GitlabIssue;
import org.gitlab.api.models.GitlabProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GLProjectPopup extends JPopupMenu {

    public GLProjectPopup( final GLIController controller, final GLIssueListMouseAdapter mouseAdapter, final GLProjectNode node ) {
        final GitlabProject project = node.getUserObject();

        JMenuItem refresh = new JMenuItem( "Refresh Issues", AllIcons.Actions.Refresh );
        refresh.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                node.removeAllChildren();
                mouseAdapter.doubleClick( node );
            }
        });
        this.add( refresh );

        JMenuItem create = new JMenuItem( "New Issue", AllIcons.General.Add );
        create.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GitlabIssue issue = new GitlabIssue();
                issue.setProjectId( project.getId() );
                controller.openEditor( issue );
            }
        });
        this.add( create );
    }

}
